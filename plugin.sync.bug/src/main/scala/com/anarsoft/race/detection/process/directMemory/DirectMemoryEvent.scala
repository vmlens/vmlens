package com.anarsoft.race.detection.process.directMemory

import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.vmlens.api.MemoryAccessType;
import com.anarsoft.race.detection.process.gen._;
// extends SyncPoint[Long] 

trait DirectMemoryEvent   {
  
  def  objectHashCode  : Long;
  
  def uniqueId = objectHashCode;
 
  
   def operation() : Int;
   
    
 
  
   
   
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
  
  def accept(visitor : DirectMemoryVisitor);
  
}