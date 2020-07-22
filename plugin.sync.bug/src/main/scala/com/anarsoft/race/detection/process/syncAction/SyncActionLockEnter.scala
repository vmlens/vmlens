package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;


trait SyncActionLockEnter extends SyncActionLock  {
  
  def isLockEnter() = true;

  
  def eventStartsHappensBeforeRelation() = false;
  def eventEndsHappensBeforeRelation() = true;
  
  
  

//  
// 
//   def syncActionType( prevoius : Option[SyncPointGeneric[Int,MonitorReference]], next : Option[SyncPointGeneric[Int,MonitorReference]])  = 
//     LockEnter(monitorId ,  prevoius.map( x =>  x.createReference() )  ,  next.map( x => x.createReference() ) );
//  
//  
  
}