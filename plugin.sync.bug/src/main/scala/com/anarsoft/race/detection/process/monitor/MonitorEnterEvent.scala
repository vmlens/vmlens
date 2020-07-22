package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.process.monitorRelation.Event4MonitorRelationEnter
import com.anarsoft.race.detection.model.result._;
import  com.anarsoft.race.detection.process._;



trait MonitorEnterEvent  extends MonitorEvent with Event4MonitorRelationEnter {
  
 
  
  
   def methodIdOption               = Some(methodId);
   def isStackTraceIncompleteOption = None;
  

  def methodId : Int;
  def eventStartsHappensBeforeRelation() = false;
  def eventEndsHappensBeforeRelation() = true;
  
  def isMonitorEnter() = true;
   

  
}