package com.anarsoft.race.detection.process.volatileField

import com.vmlens.api.MemoryAccessType;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor

trait VolatileAccessEvent extends VolatileAccessEventAbstract[Long] {
  
  
   //def objectId = Some(objectHashCode);
   
  
  def idPerMemoryLocation = objectHashCode;
  def compareIdPerMemoryLocation(  other : Long)  = java.lang.Long.compare(idPerMemoryLocation, other);
   def isStatic = false;
  
  
   def eventStartsHappensBeforeRelation() = 
   {
     
     if( MemoryAccessType.isAtomic(operation) )
     {
       true;
     }
     else
     {
        MemoryAccessType.isWriteOnly(operation)
     }
     
   }
  def eventEndsHappensBeforeRelation() = 
  {
      if( MemoryAccessType.isAtomic(operation) )
     {
       true;
     }
     else
     {
        ! MemoryAccessType.isWriteOnly(operation)
     }
  }
  
  
  def objectHashCode : Long;
  
  
  
  
  
  
    def accept(visitor : EventSetStacktraceOrdinalVisitor)
    {
      visitor.visit(this);
    }
  
  
  
}