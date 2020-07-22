package com.anarsoft.race.detection.process.syncAction


import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;


trait SyncActionLockExit  extends SyncActionLock  {
  
  def isLockEnter() = false;
  
  /*
   * All ReadWriteLock implementations must guarantee that the memory synchronization effects of writeLock operations (as specified in the Lock interface) also hold with respect to the associated readLock. That is, a thread successfully acquiring the read lock will see all updates made upon previous release of the write lock. 
   */
  
   def eventStartsHappensBeforeRelation() = true;
  
//  def eventStartsHappensBeforeRelation() = {
//    if( isShared)
//       {
//    false;
//  }
//    else
//    {
//      true;
//    }
//      
//  }
 
  def eventEndsHappensBeforeRelation() = false;
  

  
//   
//   
//   def syncActionType( prevoius : Option[SyncPointGeneric[Int,MonitorReference]], next : Option[SyncPointGeneric[Int,MonitorReference]])  = LockExit(monitorId ,  prevoius.map( x =>  x.createReference() )  ,  next.map( x => x.createReference() ) );
//  
   
}