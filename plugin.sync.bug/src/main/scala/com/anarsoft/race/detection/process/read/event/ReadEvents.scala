package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.workflow._;
import scala.collection.mutable.HashMap;
import java.util.concurrent.Executors
import com.anarsoft.race.detection.process.gen._;
import java.io._;
import com.anarsoft.race.detection.process.SystemOutProgressMonitor;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.result._;
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
import  com.anarsoft.race.detection.process.directMemory._;
import com.vmlens.api.internal.reports._;
import com.anarsoft.race.detection.process.aggregate._
import com.anarsoft.race.detection.process.setStacktraceOrdinal._
import  com.anarsoft.race.detection.process.detectRaceConditions._
import com.anarsoft.race.detection.process.syncAction._
import com.anarsoft.race.detection.process.field.ContextFieldIdMap
import com.anarsoft.race.detection.process.volatileField.ConfigVolatileFields
import com.anarsoft.race.detection.process.nonVolatileField.ConfigNonVolatileFields
import com.anarsoft.race.detection.process.monitor.ConfigMonitor
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo
import com.anarsoft.race.detection.process.perEventList._;
import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.trace.agent.runtime.process.PluginController
import com.anarsoft.race.detection.process.scheduler._;



object ReadEvents {
  
  
   def process(eventDir : String, config : ReadEventConfig, onEvent : OnEvent)
   {
    
     
     
     val maxSlidingWindowId =  PluginController.loadCurrentState(eventDir).getSlidingWindowId();
     
     println(maxSlidingWindowId);
     
     
   
     
     
    
    val listForFinally = new   HashMap[Object,ToBeClosed] 
    val executorService =  Executors.newFixedThreadPool(1);
    
   
     
    
      val processPipeline = new ProcessPipeline[ContextReadEvent]();
      
      
      processPipeline.step(new ReadDescriptionInMap(eventDir));
      
      
         processPipeline.step(new WrappedStep(new StepCreateStream[MonitorEvent,ContextReadEvent]((x, c) => c.monitorEventStreams = x, new MonitorDeSerializer() ,
          "monitor_", "monitorStatistic_", eventDir ,executorService ,  listForFinally,maxSlidingWindowId , 0) , classOf[ContextReadEvent]));

      
      
      
      
               
      processPipeline.step(    new WrappedStep(  new StepCreateStream[ApplyMethodEventVisitor,ContextReadEvent]((x, c) => c.methodEventStreams = x, new MethodDeSerializer() ,
          "method_", "methodStatistic_", eventDir , executorService , listForFinally ,maxSlidingWindowId,0)  , classOf[ContextReadEvent] ) );
      
      
        processPipeline.step( new WrappedStep( new StepCreateStream[SyncAction,ContextReadEvent]((x, c) => c.syncActionStreams = x, new  SyncActionsDeSerializer() ,  
           "syncActions_", "syncActionsStatistic_", eventDir , executorService , listForFinally , maxSlidingWindowId , 0)  , classOf[ContextReadEvent] ) );
      
      
          processPipeline.step(new WrappedStep(  new StepCreateStream[ApplyFieldEventVisitor,ContextReadEvent]((x, c) => c.fieldEventStreams = x, new FieldDeSerializer() ,  
          "field_", "fieldStatistic_", eventDir , executorService , listForFinally , maxSlidingWindowId , 0), classOf[ContextReadEvent] ) );
        
             processPipeline.step(new WrappedStep(new StepCreateStream[SchedulerEvent, ContextReadEvent]((x, c) => c.schedulerEventStreams = x, new SchedulerDeSerializer(),
        "scheduler_", "schedulerStatistic_", eventDir, executorService, listForFinally, maxSlidingWindowId, 2), classOf[ContextReadEvent]));
      
        
             
             
             processPipeline.step( new Loop[ContextReadEvent](createReadMethodAndFieldEventPipeline(onEvent,config) ,  classOf[ContextReadEvent]  , maxSlidingWindowId , processPipeline )   );
    
         
    val context = new ContextReadEvent();     
      
    processPipeline.execute(context,  new SystemOutProgressMonitor());
    
    
    
  

    
    executorService.shutdown();
    

    
   }
  
  
  
  
  
  
  
  
  
  def createReadMethodAndFieldEventPipeline( onEvent : OnEvent, config : ReadEventConfig) =
    {
    
//      val newBuildMethodOrdinalAggregate  = new StepProcessPerEventListCollectionWithName[ContextBuildMethodOrdinalAggregate](classOf[ContextBuildMethodOrdinalAggregate], "newBuildMethodOrdinalAggregate");
//    
//     
//     newBuildMethodOrdinalAggregate.processPerEventListCollection.append(
//        
//       new PerEventListBuildMethodOrdinalAggregate[Long,ArrayAccessEvent]( context => context.arrayAccessEventList )
//        
//       );
    
       val readMethodEvents =  new StepReadEvents[ApplyMethodEventVisitor,ContextReadEvent](x => x.methodEventStreams, (c) => new TraceEventCallback(onEvent,c) ) 
    
       
       
      val processPipeline = new ProcessPipeline[ContextReadEvent]();
       
        
      if(config.methods)
      {
          processPipeline.step(readMethodEvents);
      }
       
          if(config.scheduler)
       {
           processPipeline.step( new StepReadEvents[SchedulerEvent,ContextReadEvent](x => x.schedulerEventStreams, (c) => new TraceEventCallback(onEvent,c)) );
       }
      
         
       if(config.monitor)
       {
           processPipeline.step( new StepReadEvents[MonitorEvent,ContextReadEvent](x => x.monitorEventStreams, (c) => new TraceEventCallback(onEvent,c)) );
       }
         
    
     
      if(config.syncActions)
      {
           processPipeline.step( new StepReadEvents[SyncAction,ContextReadEvent](x => x.syncActionStreams, (c) => new TraceEventCallback(onEvent,c)) );
      }
      
    
      if(config.field)
      {  
     
      
        processPipeline.step(new StepReadEvents[ApplyFieldEventVisitor,ContextReadEvent](x => x.fieldEventStreams,  (  c ) =>  new TraceEventCallback(onEvent,c) ));
        
//           processPipeline.step(newBuildMethodOrdinalAggregate );
        
        // processPipeline.step( new StepCountEvents() );
      }

        
       
       
      processPipeline;
    }
  
  
  
  
  
  
  
  
  
  
}