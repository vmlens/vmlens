package com.anarsoft.race.detection.process

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
import com.anarsoft.race.detection.process.sharedState.StepCreateSharedState
import com.anarsoft.race.detection.process.facade.StepCreateFieldAndArrayPerStackTraceFacade
import java.io.File
import com.vmlens.maven.plugin.Extended
import com.anarsoft.race.detection.process.setStacktraceOrdinal.ContextSetStacktraceOrdinal
import net.liftweb.json._;
import net.liftweb.json.Extraction._
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.trace.agent.runtime.process.PluginController
import com.anarsoft.trace.agent.runtime.process.AgentController
import com.typesafe.scalalogging.Logger

abstract class ProcessTemplate[RESULT,MAIN_CONTEXT <: ContextProcessTemplate](val prozessConfig : ProzessConfig) {
  
  
  
     def createAndExecutePipeline(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int,progressMonitor: ProgressMonitor) : RESULT;
  
     def addStartSteps(eventDir : String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int ,processPipeline :  ProcessPipeline[MAIN_CONTEXT]);
     
      
  
     def addEventProcessingSteps(eventDir: String, readMethodEvents: Step[ContextMethodData] , processPipeline : ProcessPipeline[MAIN_CONTEXT], maxSlidingWindowId : Int);
     
     def addStepsBeforeStackTraceGraph( processPipeline  : ProcessPipeline[MAIN_CONTEXT]);
  
     
     def addEndSteps( eventDir : String, processPipeline  : ProcessPipeline[MAIN_CONTEXT]);
     
     
      def additionalOpOnParallizedMethodEnter ( event : ParallizedMethodEnterEvent , context : ContextMethodData );
      def additionalOpOnMethodInParallizeBlock (   event : MethodEvent , parallizedId : Int ,  context : ContextMethodData  );
      
     
  
}