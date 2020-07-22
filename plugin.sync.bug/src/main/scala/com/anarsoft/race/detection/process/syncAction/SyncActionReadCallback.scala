package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;
import  com.anarsoft.race.detection.process._;
import com.typesafe.scalalogging.Logger
import com.anarsoft.race.detection.process.workflow.ProcessPipeline
import com.anarsoft.race.detection.process.volatileField._;

class SyncActionReadCallback(val context : ContextProcessSyncAction,val pipeline : ProcessPipeline[_]) extends ReadCallback[SyncAction] {
  
    val logger = Logger(classOf[SyncActionReadCallback])
  
  
  val visitor  =new  SyncActionsVisitorImpl( context ); 
  
    def readSlidingWindowId( id : Int  )
   {
   visitor.currentReadSlidingWindowId = id;  
   
   /*
    *       lockEventList = new ArrayList[SyncActionLock];
      volatileAccessEventList = new ArrayList[VolatileAccessEvent]();
      volatileAccessEventStatic = new ArrayList[VolatileAccessEventStatic]();
      
      volatileAccessArrayEventList = new  ArrayList[VolatileArrayAccessEvent]();
    */
   
     if( context.lockEventList != null )
     {
       pipeline.traceEventCount(classOf[SyncActionLock], context.lockEventList.size())
       pipeline.traceEventCount(classOf[VolatileAccessEvent], context.volatileAccessEventList.size())
       pipeline.traceEventCount(classOf[VolatileAccessEventStatic], context.volatileAccessEventStatic.size())
       pipeline.traceEventCount(classOf[VolatileArrayAccessEvent], context.volatileAccessArrayEventList.size())
     }
   
   
   
   context.resetContextProcessSyncAction();
      
        
   }
  
   def onEvent( event : SyncAction)
   {
      logger.debug("" + event);
       event.accept(visitor);
   }
  
  
  
  
  
  
  
  
  
}