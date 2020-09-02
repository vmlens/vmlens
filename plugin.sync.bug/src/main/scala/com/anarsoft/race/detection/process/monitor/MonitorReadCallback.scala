package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.read._;
import java.util.ArrayList;
import  com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.workflow.ProcessPipeline
import com.typesafe.scalalogging.Logger

class MonitorReadCallback(val context : ContextMonitor,pipeline : ProcessPipeline[_]) extends ReadCallback[MonitorEvent] {
  
    val logger = Logger(classOf[MonitorReadCallback])
  
    def readSlidingWindowId( id : Int  )
   {
      if(  context.monitorEventList != null )
     {
       pipeline.traceEventCount(classOf[MonitorEvent], context.monitorEventList.size())
      
     }
   
     
   
   context.monitorEventList = new   ArrayList[MonitorEvent];      
        
   }
  
   def onEvent( event : MonitorEvent)
   {
        logger.trace("" + event);
     
       context.monitorEventList.add(event);
       
       
       if( event.isInstanceOf[InterleaveEventStatement] && context.interleaveEventList != null)
       {
         context.interleaveEventList.add( event.asInstanceOf[InterleaveEventStatement]  );
       }
       
       
   }
  
  
  
  
  
  
  
  
  
}