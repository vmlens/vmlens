package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.aggregate.EventStackTraceOrdinalAggregate
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass;
import com.anarsoft.race.detection.model.result.FieldInClass;
import com.anarsoft.race.detection.process.field.EventField

trait StateEventStaticField  extends StateEvent  with EventStackTraceOrdinalAggregate[Int] with WithLocationInClass with EventField  {
  
  def isVolatile = false;
  def isStatic  = true;
  
   def idPerMethod = fieldId ;
  def idPerMemoryLocation = fieldId;
  def compareIdPerMemoryLocation(  other : Int)  = java.lang.Integer.compare(idPerMemoryLocation, other);
  
 
  def  fieldId  : Int;
  
  
  def accept(visitor : StateEventVisitor)
  {
    visitor.visit(this);
  }
  
}