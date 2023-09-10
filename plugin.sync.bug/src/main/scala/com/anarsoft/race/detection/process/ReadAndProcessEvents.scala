package com.anarsoft.race.detection.process

import com.anarsoft.config.ConfigValues
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process.detectDeadlocks.{ContextDetectDeadlocks, StepBuildPotentialDeadlockWithParentMonitorIds, StepCheckPotentialDeadlocks, StepFindPotentialDeadlocks}
import com.anarsoft.race.detection.process.detectRaceConditions.ContextDetectRaceConditions
import com.anarsoft.race.detection.process.directMemory._
import com.anarsoft.race.detection.process.field._
import com.anarsoft.race.detection.process.gen._
import com.anarsoft.race.detection.process.interleave._
import com.anarsoft.race.detection.process.method._
import com.anarsoft.race.detection.process.monitor._
import com.anarsoft.race.detection.process.monitorRelation.{ContextCreateMonitorRelation, StepCreateMonitorRelation}
import com.anarsoft.race.detection.process.nonVolatileField._
import com.anarsoft.race.detection.process.partialOrder.{ContextCreatePartialOrder, StepCreateNewPartialOrderBuilder4SlidingWindowId}
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.read._
import com.anarsoft.race.detection.process.result._
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process.syncAction._
import com.anarsoft.race.detection.process.workflow._
import com.anarsoft.trace.agent.runtime.process.{AgentController, PluginController}
import com.typesafe.scalalogging.Logger

import java.io.File
import java.util.concurrent.{ExecutorService, Executors}
import scala.collection.mutable.HashMap
/**
 *
 * einlesen und prozessieren der events:
 *
 * 	1) monitor files
 * 	2) method, sync statements, fields
 *  3) description
 *
 *
 *
 *
 */

class ReadAndProcessEvents(prozessConfig: ProzessConfig, val configValues: ConfigValues) {

  val logger = Logger("com.vmlens.Performance")

  // ModelFacadeAll

  def createPipeline(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int) =
    {

      val processPipeline = new ProcessPipeline[MainContextReadAndProcess]();

      processPipeline.step(new StepReadThreadNames(eventDir));
      // processPipeline.step(new StepReadPropertiesAndCreateFilter(eventDir));

      processPipeline.step(new WrappedStep(new StepCreateStream[ApplyMethodEventVisitor, ContextMethodData]((x, c) => c.methodEventStreams = x, new MethodDeSerializer(),
        "method_", "methodStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 0), classOf[ContextMethodData]));

      processPipeline.step(new StepInitializeMethodData());

      addStartSteps(eventDir, executorService, listForFinally, maxSlidingWindowId, processPipeline);

      processPipeline.step(new StepReadStackTraceEvents(eventDir));

      processPipeline.step(new WrappedStep(new StepCall[ContextStackTraceGraphBuilder](context => context.initializeContextStackTraceGraphBuilder()), classOf[ContextStackTraceGraphBuilder]));

      processPipeline.step(new StepIncrementProgressMonitor[MainContextReadAndProcess](5));

      // start der Prozessierung der felder in sliding window ids

      val readMethodEvents = readAndProzessMethodEvents(processPipeline);

      // Methods müssen zwei slidingWindow Ids vorher gelesen werden

      processPipeline.step(readMethodEvents);
      processPipeline.step(readMethodEvents);

      addEventProcessingSteps(eventDir, readMethodEvents, processPipeline, maxSlidingWindowId);

     

      processPipeline.step(new StepCreateStackTraceGraph())

      processPipeline.step(new StepAddStacktraceIdsAndMissingLink());

      processPipeline.step(new StepIncrementProgressMonitor[MainContextReadAndProcess](5));

      processPipeline.step(new StepReadAgentLog(eventDir, prozessConfig));

 

    processPipeline.step(new StepReadClassName(eventDir));

    processPipeline.step(new StepReadDescription(eventDir));


     processPipeline.step(new StepBuildFieldAndArrayFacade());
  
     processPipeline.step(new StepFilterRaceConditions(configValues));
     
      processPipeline.step(new StepProcessInterleaveEventListAfterRead());

      processPipeline.step(new StepCreateModelFacade());

      processPipeline;

    }

  def readAndProzessMethodEvents(processPipeline: ProcessPipeline[MainContextReadAndProcess]) =
    {
      val compositeStep = new CompositeStep[ContextMethodData]("readAndProzessMethodEvents", processPipeline);

      // Fixmw Plugin
      //compositeStep.step(new StepReadEvents[ApplyMethodEventVisitor, ContextMethodData](x => x.methodEventStreams, (c) => new MethodReadCallback(c, processPipeline)));

      compositeStep.step(new StepProzessMethodEvents());


      compositeStep

    }

  def prozessMavenPlugin(eventDir: String, progressMonitor: ProgressMonitor) =
    {

      val state = PluginController.loadCurrentState(eventDir);

      if (state.getState != AgentController.STATE_STOPPED) {
        throw new RuntimeException("vmlens agent is still running");
      }

      prozessWithMonitor(eventDir: String, progressMonitor, state.getSlidingWindowId());
    }

  def prozessAll(eventDir: String) =
    {

      val agentState = PluginController.loadCurrentState(eventDir);

      val slidingWindowId = agentState.getSlidingWindowId;

      println(agentState)

      prozess(eventDir: String, slidingWindowId)
    }

  def prozess(eventDir: String, maxSlidingWindowId: Int) =
    {
      prozessWithMonitor(eventDir, new SystemOutProgressMonitor(), maxSlidingWindowId);
    }

  def eventDirOK(eventDir: String) = {
    var eventFileCount = 0;
    val dir = new File(eventDir)

    for (file <- dir.listFiles()) {
      if (file.getName().endsWith(".vmlens")) {
        eventFileCount = eventFileCount + 1;
      }
    }

    eventFileCount != 0
  }


  def prozessWithMonitor(eventDir: String, progressMonitor: ProgressMonitor, maxSlidingWindowId: Int) =
    {

      if (eventDirOK(eventDir)) {
        val listForFinally = new HashMap[Object, ToBeClosed]
        val executorService = Executors.newFixedThreadPool(1);

        //          val pipeline = createPipeline(eventDir, executorService, listForFinally, maxSlidingWindowId);
        //
        //          val context = new ContextReadAndProcess();

        try {

          logger.trace("maxSlidingWindowId " + maxSlidingWindowId);

          createAndExecutePipeline(eventDir, executorService, listForFinally, maxSlidingWindowId, progressMonitor: ProgressMonitor);

        } finally {
          for (e <- listForFinally) {
            e._2.close();
          }

          executorService.shutdownNow();

        }

      } else {
        throw new NoEventFilesException();
      }

    }


  val perEventListSteps = PerEventListStepCollection();

  def addStartSteps(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int, processPipeline: ProcessPipeline[MainContextReadAndProcess]) {

    processPipeline.step(new WrappedStep(new StepCall[ContextLastProgramCounter](context => context.initializeContextLastProgramCounter()), classOf[ContextLastProgramCounter]));

    //   Create Heap Dump
    //      processPipeline.step(new   StepCreateHeapDump());

    processPipeline.step(new WrappedStep(new StepCall[ContextProcessSyncAction](context => context.initializeContextSyncAction()), classOf[ContextProcessSyncAction]));

    processPipeline.step(new WrappedStep(new StepCreateStream[MonitorEvent, ContextMonitor]((x, c) => c.monitorEventStreams = x, new MonitorDeSerializer(),
      "monitor_", "monitorStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 1), classOf[ContextMonitor]));

    processPipeline.step(new WrappedStep(new StepCreateStream[SyncAction, ContextProcessSyncAction]((x, c) => c.syncActionStreams = x, new SyncActionsDeSerializer(),
      "syncActions_", "syncActionsStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 1), classOf[ContextProcessSyncAction]));

    processPipeline.step(new WrappedStep(new StepCreateStream[ApplyFieldEventVisitor, ContextNonVolatileFields]((x, c) => c.fieldEventStreams = x, new FieldDeSerializer(),
      "field_", "fieldStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 5), classOf[ContextNonVolatileFields]));

    processPipeline.step(new WrappedStep(new StepCreateStream[DirectMemoryEvent, ContextReadDirectMemory]((x, c) => c.directMemoryEventStreams = x, new DirectMemoryDeSerializer(),
      "directMemory_", "directMemoryStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 2), classOf[ContextReadDirectMemory]));

    processPipeline.step(new WrappedStep(new StepCreateStream[SchedulerEvent, ContextSchedulerRead]((x, c) => c.schedulerEventStreams = x, new SchedulerDeSerializer(),
      "scheduler_", "schedulerStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 2), classOf[ContextSchedulerRead]));

    processPipeline.step(new StepCall[ContextCreatePartialOrder](c => c.initializeContextCreatePartialOrder()));
    processPipeline.step(new StepCall[ContextBuildAggregate](c => c.initializeContextBuildAggregate()));
    processPipeline.step(new StepCall[ContextDetectRaceConditions](c => c.initializeContextDetectRaceConditions()));
    processPipeline.step(new StepCall[ContextFieldIdMap](c => c.initializeContextFieldIdMap()));

    processPipeline.step(new StepCall[ContextCreateMonitorRelation](c => c.initializeContextCreateMonitorRelation()));
    processPipeline.step(new StepCall[ContextDetectDeadlocks](c => c.initializeContextDetectDeadlocks()));

    processPipeline.step(new StepCall[ContextInterleave](c => c.initializeContextInterleave()));

    // processPipeline.step(new StepCall[ContextInterleave](  c => c.initializeContextMergeEvents4Statements() ));

  }

  def addEventProcessingSteps(eventDir: String, readMethodEvents: Step[ContextMethodData], processPipeline: ProcessPipeline[MainContextReadAndProcess], maxSlidingWindowId: Int) {
    val stepReadAndProzessSyncActionAndMonitorEvents = readAndProzessSyncActionAndMonitorEvents(perEventListSteps, processPipeline);

    processPipeline.step(stepReadAndProzessSyncActionAndMonitorEvents);

    processPipeline.step(new Loop[ContextReadMethodAndField](
      createReadMethodAndFieldEventPipeline(eventDir, readMethodEvents, stepReadAndProzessSyncActionAndMonitorEvents, perEventListSteps, processPipeline),
      classOf[ContextReadMethodAndField], maxSlidingWindowId, processPipeline));

  }

 
  

  def readAndProzessSyncActionAndMonitorEvents(perEventListSteps: PerEventListStepCollection, processPipeline: ProcessPipeline[MainContextReadAndProcess]) =
    {

      val compositeStep = new CompositeStep[ContextReadAndProzessSyncActionAndMonitorEvents]("readAndProzessSyncActionAndMonitorEvents", processPipeline);

      compositeStep.step(new StepCreateNewPartialOrderBuilder4SlidingWindowId());
         compositeStep.step(new StepReadEvents[SyncAction, ContextProcessSyncAction](x => x.syncActionStreams, (c) => new SyncActionReadCallback(c, processPipeline)));
      compositeStep.step(new StepReadEvents[MonitorEvent, ContextMonitor](x => x.monitorEventStreams, (c) => new MonitorReadCallback(c, processPipeline)));

    
      
      
      compositeStep.step(perEventListSteps.setArrayOrdinalInterleave);
      compositeStep.step(perEventListSteps.setFieldOrdinal);
      compositeStep.step(perEventListSteps.setStacktraceOrdinal);
      compositeStep.step(perEventListSteps.buildMethodOrdinalAggregate);


      compositeStep.step(new StepCreateMonitorRelation())
      compositeStep.step(new StepFindPotentialDeadlocks())
      compositeStep.step(new StepBuildPotentialDeadlockWithParentMonitorIds())
      compositeStep.step(new StepCheckPotentialDeadlocks())

   

      prozessConfig.checkLocksStep() match {
        case None =>
          {

          }
        case Some(x) =>
          {
            compositeStep.step(x);
          }

      }
      
        compositeStep.step(new StepAddMonitorEvents2InterleaveList());

      compositeStep.step(perEventListSteps.prozessSyncPointLists);
      compositeStep.step(new StepProzessThreadStoppedEvent());

      compositeStep.step(new StepAddCurrentlyBuilded2PartialOrder());

      compositeStep;
    }

  def createReadMethodAndFieldEventPipeline(eventDir: String, readMethodEvents: Step[ContextMethodData], stepReadAndProzessSyncActionAndMonitorEvents: Step[ContextReadAndProzessSyncActionAndMonitorEvents],
    perEventListSteps: PerEventListStepCollection, pipeline: ProcessPipeline[_]) =
    {
      val processPipeline = new ProcessPipeline[ContextReadMethodAndField]();
      processPipeline.step(readMethodEvents);
      processPipeline.step(new StepReadEvents[ApplyFieldEventVisitor, ContextNonVolatileFields](x => x.fieldEventStreams, (c) => new FieldReadCallback(c, pipeline)));

      processPipeline.step(new StepReadEvents[SchedulerEvent, ContextSchedulerRead](x => x.schedulerEventStreams, (c) => new SchedulerReadCallback(c)));

      processPipeline.step(new StepFilterNonVolatileFields());

      processPipeline.step(new StepAddNonVolatileFields2InterleaveList());

      // Actung muss nach einlesen kommen (Ansonsten stimmt die slidingWindowId nicht)
      processPipeline.step(stepReadAndProzessSyncActionAndMonitorEvents);

      processPipeline.step(perEventListSteps.detectRaceConditions);
      processPipeline.step(perEventListSteps.setMonitorInfo4NonVolatile);

      
      
   
      
      
      
      processPipeline.step(new StepProcessInterleaveEventListDuringRead());


      additionalMethodAndFields( processPipeline);
      

      processPipeline;
    }

  
  def additionalMethodAndFields( processPipeline : ProcessPipeline[ContextReadMethodAndField])
  {
    
    
  }
  
  
  def createAndExecutePipeline(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int, progressMonitor: ProgressMonitor) =
    {
      val pipeline = createPipeline(eventDir, executorService, listForFinally, maxSlidingWindowId);

      val context = new MainContextReadAndProcess();

      pipeline.execute(context, progressMonitor);

      context.modelFacade;

    }

}

object ReadAndProcessEvents {

  def create4Test(configValues: ConfigValues) = {
    new ReadAndProcessEvents(new ProzessConfigTest(), configValues);
  }

  def create4Prod(configValues: ConfigValues) = {
    new ReadAndProcessEvents(new ProzessConfigProd(), configValues);
  }

  def create4ItTest() = {
    new ReadAndProcessEvents(new ProzessConfigTest(), new TestConfigValues());
  }

}