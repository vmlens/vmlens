package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.field.EventField
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId


trait NonVolatileFieldAccessEventStatic   extends  AbstractNonVolatileMemoryAccessEvent[Int] with EventField  
{
 
  def idPerMemoryLocation = fieldId;
  def compareIdPerMemoryLocation(  other : Int)  = java.lang.Integer.compare(idPerMemoryLocation, other);
  def idPerMethod = fieldId;
  
  
  def fieldId : Int;
  def isStatic = true;
  
   
  
  
    var stackTraceOrdinal: Int;
  var parallizeId : Option[Int] = None;
   
  
   def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     stackTraceOrdinal = in.ordinal;
     parallizeId = in.parallizeId;
   }
 
   
   
  

     def accept(visitor : EventSetStacktraceOrdinalVisitor)
    {
      visitor.visit(this);
    }
  
  
   
}