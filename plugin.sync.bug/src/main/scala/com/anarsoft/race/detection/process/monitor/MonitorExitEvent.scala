package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationExit

trait MonitorExitEvent extends MonitorEvent with Event4MonitorRelationExit {
  
  
   def methodIdOption               = Some(methodId);
   def isStackTraceIncompleteOption = None;
   
  def methodId : Int;
  def eventStartsHappensBeforeRelation() = true;
  def eventEndsHappensBeforeRelation() = false;
  
   def isMonitorEnter() = false;
   
      
  def isThreadSafe = false;
   
    
   
//   def createSyncStatement() = None;
// 
//   
//     def syncActionType( prevoius : Option[SyncPointGeneric[Int]], next : Option[SyncPointGeneric[Int,MonitorReference]])  = MonitorExit(monitorId ,  prevoius.map( x =>  x.createReference() )  ,  next.map( x => x.createReference() ) );
// 
   
}