package com.anarsoft.race.detection.process.mode.monitor

import com.anarsoft.race.detection.process.workflow._;
import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.result._;
import collection.JavaConverters._
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.nonVolatileField._;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.vmlens.api._;
import java.io._;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.process.gen._;
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.anarsoft.race.detection.process.directMemory._;
import com.vmlens.api.internal.reports._;
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.partialOrder.StepCreateNewPartialOrderBuilder4SlidingWindowId
import com.vmlens .api.internal.ModelFilter
import com.anarsoft.race.detection.process.aggregate.ContextBuildAggregate
import com.anarsoft.race.detection.process.detectRaceConditions.ContextDetectRaceConditions
import com.anarsoft.race.detection.process.monitorRelation.ContextCreateMonitorRelation
import com.anarsoft.race.detection.process.detectDeadlocks.ContextDetectDeadlocks
import com.anarsoft.race.detection.process.monitorRelation.StepCreateMonitorRelation
import com.anarsoft.race.detection.process.detectDeadlocks.StepFindPotentialDeadlocks
import com.anarsoft.race.detection.process.detectDeadlocks.StepBuildPotentialDeadlockWithParentMonitorIds
import com.anarsoft.race.detection.process.detectDeadlocks.StepCheckPotentialDeadlocks
import com.anarsoft.race.detection.process.perEventList.PerEventListStepCollection
import com.anarsoft.race.detection.process.sharedState.StepCreateSharedMonitor
import com.anarsoft.race.detection.process.facade.StepCreateFieldAndArrayPerStackTraceFacade
import java.io.File
import com.vmlens.maven.plugin.Extended
import com.anarsoft.race.detection.process.setStacktraceOrdinal.ContextSetStacktraceOrdinal4OwnerOfMonitor
import net.liftweb.json._;
import net.liftweb.json.Extraction._
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.PerEventListSetStacktraceOrdinal4OwnerOfMonitor
import com.anarsoft.race.detection.process.sharedState.StepAnnotateStackTraceGraphWithMonitor

class ProcessMonitor ( prozessConfig : ProzessConfig) extends  ProcessTemplate[ModelFacadeMonitor,MainContextMonitor](prozessConfig)  {
  
  
   def additionalOpOnParallizedMethodEnter ( event : ParallizedMethodEnterEvent , context : ContextMethodData )
     {
       
     }
   
    def additionalOpOnMethodInParallizeBlock (   event : MethodEvent , parallizedId : Int ,  context : ContextMethodData  )
    {
      
    }
   
   
  
    def addStartSteps(eventDir : String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int ,processPipeline :  ProcessPipeline[MainContextMonitor])
    {
     
      
      processPipeline.step(new WrappedStep(new StepCreateStream[MonitorEvent, ContextMonitor]((x, c) => c.monitorEventStreams = x, new MonitorDeSerializer(),
        "monitor_", "monitorStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 1), classOf[ContextMonitor]));
      
       
  //    processPipeline.step(new StepCall[ContextBuildAggregate](  c => c.initializeContextBuildAggregate() ));

      
      
         processPipeline.step(new StepCall[ContextMethodId2Ordinal](  c => c.initializeContextMethodId2Ordinal() ));
             
      processPipeline.step(new StepCall[ContextSetStacktraceOrdinal4OwnerOfMonitor](  c => c.initializeContextSetStacktraceOrdinal() ));
      
      processPipeline.step(new StepCall[ContextCreateMonitorRelation](  c => c.initializeContextCreateMonitorRelation() ));
      processPipeline.step(new StepCall[ContextDetectDeadlocks](  c => c.initializeContextDetectDeadlocks() ));
      

    }
    
     
     def addAdditionalSteps4MethodEvents(compositeStep : CompositeStep[ContextMethodData])
     {
       
     }
     
     
  
     def addEventProcessingSteps(eventDir: String, readMethodEvents: Step[ContextMethodData] ,  processPipeline : ProcessPipeline[MainContextMonitor],maxSlidingWindowId : Int)
     {
          processPipeline.step(new Loop[ContextReadMethodAndMonitorEvent](createReadMethodAndMonitorEventPipeline(eventDir, readMethodEvents,processPipeline), classOf[ContextReadMethodAndMonitorEvent],
              maxSlidingWindowId , processPipeline));
     }
     
     
     def addStepsBeforeStackTraceGraph( processPipeline  : ProcessPipeline[MainContextMonitor])
     {
       
     }
      
     
     
     def addEndSteps( eventDir : String, processPipeline  : ProcessPipeline[MainContextMonitor])
     {
              
          processPipeline.step(new StepReadDescriptionMethodOnly(eventDir));
       
           processPipeline.step( new StepAnnotateStackTraceGraphWithMonitor() ); 
          
          
          processPipeline.step( new StepCreateSharedMonitor() ); 
   
          processPipeline.step(new StepCreateModelFacadeMonitor());
     }
  

     
     def createReadMethodAndMonitorEventPipeline(eventDir: String, readMethodEvents: Step[ContextMethodData],pipeline : ProcessPipeline[_]) =
     {
             val compositeStep = new ProcessPipeline[ContextReadMethodAndMonitorEvent];
        
       compositeStep.step(readMethodEvents);
    
      compositeStep.step(new StepReadEvents[MonitorEvent, ContextMonitor](x => x.monitorEventStreams, (c) => new MonitorReadCallback(c,pipeline)));

      
      
       compositeStep.step(PerEventListSetStacktraceOrdinal4OwnerOfMonitor());

      
      
      
      
      compositeStep.step(new StepCreateMonitorRelation())
      compositeStep.step(new StepFindPotentialDeadlocks())
      compositeStep.step(new StepBuildPotentialDeadlockWithParentMonitorIds())
      compositeStep.step(new StepCheckPotentialDeadlocks())
      
      




      compositeStep;
     }
     
     

 
  
   def createAndExecutePipeline(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int,progressMonitor: ProgressMonitor) =
   {
          val pipeline = createPipeline(eventDir, executorService, listForFinally, maxSlidingWindowId );

          val context = new MainContextMonitor();

            pipeline.execute(context, progressMonitor);
           
            context.modelFacadeMonitor;
            
   }
  
  

}


