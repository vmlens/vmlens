package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.aggregate.EventStackTraceOrdinalAggregate
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass;
import com.anarsoft.race.detection.model.result.ArrayInClass;
import com.anarsoft.race.detection.process.aggregate.EventMethodOrdinalAggregate
import com.anarsoft.race.detection.process.arrayId.ArrayIdEvent

trait StateEventArray  extends StateEvent with EventStackTraceOrdinalAggregate[Long] with ArrayIdEvent with EventMethodOrdinalAggregate[Long] {
  
  
  
  
  
   def idPerMethod = position ;
  def idPerMemoryLocation = objectHashCode;
  def compareIdPerMemoryLocation(  other : Long)  = java.lang.Long.compare(idPerMemoryLocation, other);
  
  def objectHashCode  : Long;
  def  position  : Int;
  
   def accept(visitor : StateEventVisitor)
   {
     visitor.visit(this);
   }
  
  
  
}