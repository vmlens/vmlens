package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;
import  com.anarsoft.race.detection.process._;
import java.io._;
import  com.anarsoft.race.detection.process.method.MethodEvent
import com.anarsoft.race.detection.process.nonVolatileField.NonVolatileFieldAccessEvent
import  com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.nonVolatileField.ArrayAccessEvent


class TraceEventCallback[EVENT <: Object](val onEvent : OnEvent,val readMethodEventsContext : ContextReadEvent) extends ReadCallback[EVENT] {
  
 
  
  
 
  
  
   def onEvent( event : EVENT)
   {
     
       onEvent.onEvent(event,readMethodEventsContext);

    
   }
  
  def readSlidingWindowId( id : Int  )
  {
     
  }
  
  
}