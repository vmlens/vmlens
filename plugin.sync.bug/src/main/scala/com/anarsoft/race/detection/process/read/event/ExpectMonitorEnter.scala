package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.process.monitor.MonitorEnterEvent

case class ExpectMonitorEnter(val methodName : String) extends ExpectedEvent {
  
  def isExpected(event : Object, context : ContextReadEvent) =
  {
     if( event.isInstanceOf[MonitorEnterEvent] )
    {
      val m =  event.asInstanceOf[MonitorEnterEvent];
      
      val name = context.methodId2Name.get(m.methodId).get
      
     name.equals(methodName)
      
        
      
    }
     else
     {
       false;
     }
     
     
     
  }
  
  
  
}