package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.process.volatileField.VolatileAccessEvent

case class ExpectVolatileAccessEvent(val methodName : String,val fieldName : String) extends ExpectedEvent {
  
  def isExpected(event : Object, context : ContextReadEvent) =
  {
     if( event.isInstanceOf[VolatileAccessEvent] )
    {
      val m =  event.asInstanceOf[VolatileAccessEvent];
      
      val name = context.methodId2Name.get(m.methodId).get
      
      val field = context.fieldId2Name.get(m.fieldId).get
      
     name.equals(methodName) && field.equals(fieldName)
      
        
      
    }
     else
     {
       false;
     }
     
     
     
  }
  
  
  
}