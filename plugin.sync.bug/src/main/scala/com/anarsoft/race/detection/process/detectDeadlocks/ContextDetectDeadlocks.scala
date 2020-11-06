package com.anarsoft.race.detection.process.detectDeadlocks

import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor

import java.util.ArrayList
import  com.anarsoft.race.detection.process.monitorRelation._
import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result.Deadlock



trait ContextDetectDeadlocks {
  
  var threadId2MonitorStack4DetectDeadlocks : HashMap[Long,Stack[Event4MonitorRelationEnter]] = null;
  var potentialDeadlockWithParentMonitorIdsList : ArrayList[PotentialDeadlock] = null;
  var potentialDeadlockMap  : ThreadId2PotentialDeadlock = null;
  
  var deadlocks : HashSet[Deadlock] = null;
  var deadlockFilter : HashSet[StackTraceOrdinalAndMonitor] = null;
  
  
   def monitorRelationList : ArrayList[MonitorRelation];
   def monitorEventList     :    ArrayList[MonitorEvent];
   
   
   
   
   def initializeContextDetectDeadlocks()
   {
     threadId2MonitorStack4DetectDeadlocks = new HashMap[Long,Stack[Event4MonitorRelationEnter]]();
     
      deadlockFilter = new HashSet[StackTraceOrdinalAndMonitor]
     deadlocks = new  HashSet[Deadlock]();
   }
   
  
   def resetContextDetectDeadlocks()
   {
     potentialDeadlockWithParentMonitorIdsList = new ArrayList[PotentialDeadlock] 
   }
   
   
}