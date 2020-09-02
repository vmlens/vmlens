package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;
import  com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import com.anarsoft.race.detection.process.workflow.ProcessPipeline
import com.typesafe.scalalogging.Logger

class FieldReadCallback(val context : ContextNonVolatileFields,pipeline : ProcessPipeline[_])
   extends ReadCallback[ApplyFieldEventVisitor] {
  
   val logger = Logger(classOf[FieldReadCallback])
  var currentReadSlidingWindowId = -1
 
  
    def readSlidingWindowId( slidingWindowId : Int )
   {
     currentReadSlidingWindowId = slidingWindowId;
     
     
     /*
      *  arrayAccessEventList = new ArrayList[ArrayAccessEvent]();
    nonVolatileFieldAccessEventList = new   ArrayList[NonVolatileFieldAccessEvent]
    nonVolatileFieldAccessEventStatic = new  ArrayList[NonVolatileFieldAccessEventStatic] 
      */
     
     if( context.arrayAccessEventList != null )
     {
       pipeline.traceEventCount(classOf[ArrayAccessEvent], context.arrayAccessEventList.size())
       pipeline.traceEventCount(classOf[NonVolatileFieldAccessEvent], context.nonVolatileFieldAccessEventList.size())
       pipeline.traceEventCount(classOf[NonVolatileFieldAccessEventStatic], context.nonVolatileFieldAccessEventStatic.size())
       
     }
     
     
     
     context.resetContextNonVolatileFields();
   }
  
  
    def onEvent( event : ApplyFieldEventVisitor)
    {
      
       event.slidingWindowId = currentReadSlidingWindowId;
       
       logger.trace("" + event);
       
       context.addNonVolatileField(event);
      
     
    }
  
  
  
  
  
}