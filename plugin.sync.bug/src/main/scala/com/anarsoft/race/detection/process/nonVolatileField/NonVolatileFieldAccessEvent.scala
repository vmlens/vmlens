package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.field.EventField
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId


trait NonVolatileFieldAccessEvent  extends  AbstractNonVolatileMemoryAccessEvent[NonVolatileFieldId] with EventField 
{
  
     def idPerMemoryLocation = NonVolatileFieldId(objectHashCode,fieldId);
     def compareIdPerMemoryLocation(  other : NonVolatileFieldId)  =  idPerMemoryLocation.compare(other);
      def idPerMethod = fieldId;
  
  
    def objectHashCode : Long;
  
    def fieldId : Int;
    def isStatic = false;
  
  
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