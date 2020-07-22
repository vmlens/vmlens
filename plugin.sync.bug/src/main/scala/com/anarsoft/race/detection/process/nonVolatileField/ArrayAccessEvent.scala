package com.anarsoft.race.detection.process.nonVolatileField


import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal._;
import com.anarsoft.race.detection.process.detectRaceConditions._;
import com.anarsoft.race.detection.process.arrayId.ArrayIdEvent
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId


trait ArrayAccessEvent extends EventMethodOrdinalAggregate[Long] with   AbstractNonVolatileMemoryAccessEvent[Long] with  ArrayIdEvent
{
  
  
  
  def idPerMethod = position() ;
  def idPerMemoryLocation = objectHashCode;
  def compareIdPerMemoryLocation(  other : Long)  = java.lang.Long.compare(idPerMemoryLocation, other);
  
  
  
  
  
  
  
  
  def objectHashCode : Long;
  
  def methodId  : Int;
  
  
  def positionAsOption() = Some(position());
  
  def position() : Int;
  
  
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