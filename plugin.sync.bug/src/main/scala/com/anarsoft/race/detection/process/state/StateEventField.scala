package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.aggregate.EventStackTraceOrdinalAggregate
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass;
import com.anarsoft.race.detection.model.result.ClassAccess;


trait StateEventField extends StateEvent with EventStackTraceOrdinalAggregate[Long] with WithLocationInClass  {
 
  
  var classOrdinal = -1;
  
  
  def getLocationInClass() =
  {

    new ClassAccess(classOrdinal);
    
  }
  
  
   def idPerMethod = classId ;
  def idPerMemoryLocation = objectHashCode;
  def compareIdPerMemoryLocation(  other : Long)  = java.lang.Long.compare(idPerMemoryLocation, other);
  
  def objectHashCode  : Long;
  def  classId  : Int;
  
   def accept(visitor : StateEventVisitor)
   {
     visitor.visit(this);
   }
  
}