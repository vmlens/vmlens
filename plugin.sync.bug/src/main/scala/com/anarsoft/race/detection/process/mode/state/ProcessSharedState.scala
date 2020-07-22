package com.anarsoft.race.detection.process.mode.state


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
import com.anarsoft.race.detection.process.sharedState.StepAnnotateStackTraceGraphWithStateAndCheckStateless
import com.anarsoft.race.detection.process.facade.StepCreateFieldAndArrayPerStackTraceFacade
import java.io.File
import com.vmlens.maven.plugin.Extended
import com.anarsoft.race.detection.process.setStacktraceOrdinal.ContextSetStacktraceOrdinal
import net.liftweb.json._;
import net.liftweb.json.Extraction._
import com.anarsoft.race.detection.process.scheduler._
import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.process.partialOrder.ContextCreatePartialOrder
import com.anarsoft.race.detection.process.state._


class ProcessSharedState(  prozessConfig : ProzessConfig ) extends  ProcessTemplate[ModelFacadeState,MainContextSharedState](prozessConfig)  {
  
        val perEventListSteps =  PerEventListStepCollection.create4State();
  
  
     def additionalOpOnParallizedMethodEnter ( event : ParallizedMethodEnterEvent , context : ContextMethodData )
     {
       
     }
  
  def additionalOpOnMethodInParallizeBlock (   event : MethodEvent , parallizedId : Int ,  context : ContextMethodData  )
    {
      
    }
    
    def addStartSteps(eventDir : String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int ,processPipeline :  ProcessPipeline[MainContextSharedState])
    {

      processPipeline.step(new WrappedStep(new StepCreateStream[StateEvent, ContextState]((x, c) => c.stateEventStreams = x, new StateDeSerializer(),
        "state_", "stateStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 2), classOf[ContextState]));
      
      
       
      
      
       processPipeline.step(new WrappedStep(new StepCreateStream[StateEventInitial, ContextInitialState]((x, c) => c.initialStateEventStreams = x, new StateInitialDeSerializer(),
        "stateInitial_", "stateInitialStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 0), classOf[ContextInitialState]));
      
      processPipeline.step(new StepCall[ContextInitialState](      c => c.initializeContextInitialState(eventDir) ));
      processPipeline.step(new StepCall[ContextBuildAggregate](    c => c.initializeContextBuildAggregate() ));
      processPipeline.step(new StepCall[ContextFieldIdMap4State](  c => c.initializeContextFieldIdMap() ));

      
      val compositeStepPreprocessInitialState = new ProcessPipeline[ContextInitialState];
      compositeStepPreprocessInitialState.step(new StepReadEvents[StateEventInitial, ContextInitialState](x => x.initialStateEventStreams, (c) => new InitialStateReadCallback(c)));
      
      processPipeline.step(new Loop[ContextInitialState](compositeStepPreprocessInitialState, 
        classOf[ContextInitialState] , maxSlidingWindowId  , processPipeline));
      
     processPipeline.step(new StepProcessLastInitialEvents());
     
     
      processPipeline.step(new WrappedStep(new StepCreateStream[StateEvent, ContextState]((x, c) => c.tempStateEventStreams = x, new StateDeSerializer(),
        "tempState_", "tempStateStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 2), classOf[ContextState]));
      
     
     
     
     
     
     
    }
    
     
     def addAdditionalSteps4MethodEvents(compositeStep : CompositeStep[ContextMethodData])
     {
       
     }
     
     
  
     def addEventProcessingSteps(eventDir: String, readMethodEvents: Step[ContextMethodData] ,  processPipeline : ProcessPipeline[MainContextSharedState],maxSlidingWindowId : Int)
     {
           val compositeStep = new ProcessPipeline[ContextReadMethodAndStateEvent];
        
       compositeStep.step(readMethodEvents);
    

         compositeStep.step(new StepReadEvents[StateEvent, ContextState](x => x.stateEventStreams, (c) => new StateReadCallback(c)));
         compositeStep.step(new StepReadEvents[StateEvent, ContextState](x => x.tempStateEventStreams, (c) => new TempStateReadCallback(c)));  
         
          compositeStep.step(new StepBuildClassIdMap());    
         
          compositeStep.step(perEventListSteps.setArrayOrdinalState);    
          
         compositeStep.step(PerEventListBuildFieldIdMap4State());    
         compositeStep.step(PerEventCallbackBuildMethodOrdinalAggregate4State()); 
             
         compositeStep.step(perEventListSteps.setStacktraceOrdinal4OwnerOfState);
         
         
           compositeStep.step(perEventListSteps.buildStackTraceOrdinalAggregate);
         
         
           
           
           
           
           
           
           
          
             processPipeline.step(new Loop[ContextReadMethodAndStateEvent](compositeStep, 
        classOf[ContextReadMethodAndStateEvent] , maxSlidingWindowId , processPipeline ));
         
     
           
           
     }
     

//     
//      def createReadMethodAndFieldEventPipeline(eventDir: String, readMethodEvents: Step[ContextMethodData]) =
//     {
//             val compositeStep = new ProcessPipeline[ContextReadMethodAndFieldEvent];
//        
//       compositeStep.step(readMethodEvents);
//    
//
//         compositeStep.step(new StepReadEvents[ApplyFieldEventVisitor, ContextNonVolatileFields](x => x.fieldEventStreams, (c) => new FieldReadCallback(c)));
//      
//      compositeStep.step(new StepFilterArrayAccess());
//       
//       
//      compositeStep.step(perEventListSteps.setFieldOrdinal);
//      compositeStep.step(perEventListSteps.setStacktraceOrdinal4OwnerOfState);
//      
//
//      
//      
//      compositeStep.step(perEventListSteps.buildMethodOrdinalAggregate);
//      
//      
//       compositeStep.step(perEventListSteps.setSortable);
//
//      compositeStep.step(perEventListSteps.buildStackTraceOrdinalAggregate);
//
//
//      compositeStep;
//     }
     
     
     
        

     
     
     
     
     def addStepsBeforeStackTraceGraph( processPipeline  : ProcessPipeline[MainContextSharedState])
     {
          processPipeline.step(new StepCreateFieldAndArrayPerStackTraceFacade());
     }
      
     
     
     def addEndSteps( eventDir : String, processPipeline  : ProcessPipeline[MainContextSharedState])
     {
              
       processPipeline.step(new StepReadDescription(eventDir));
     
        processPipeline.step( new StepReadClassName(eventDir) ); 
       
       
      processPipeline.step(new StepBuildFieldAndArrayFacade4State());
        
      processPipeline.step( new StepAnnotateStackTraceGraphWithStateAndCheckStateless() ); 
        
        
      processPipeline.step( new StepCreateSharedState() ); 
   
      processPipeline.step(new StepCreateModelFacadeState());
        
     }
  

  
  
  

 
  
   def createAndExecutePipeline(eventDir: String, executorService: ExecutorService, listForFinally: HashMap[Object, ToBeClosed], maxSlidingWindowId: Int,progressMonitor: ProgressMonitor) =
   {
          val pipeline = createPipeline(eventDir, executorService, listForFinally, maxSlidingWindowId );

          val context = new MainContextSharedState();

            pipeline.execute(context, progressMonitor);
           
            context.modelFacadeState;
            
   }
  
  

}


