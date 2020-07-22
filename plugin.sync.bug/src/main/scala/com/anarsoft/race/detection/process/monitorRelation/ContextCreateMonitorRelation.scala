package com.anarsoft.race.detection.process.monitorRelation

import scala.collection.mutable.HashMap;
import scala.collection.mutable.Stack
import java.util.ArrayList
import com.anarsoft.race.detection.process.monitor.MonitorEvent


trait ContextCreateMonitorRelation {
  
  var threadId2MonitorStack : HashMap[Long,Stack[BlockIdAndEvent4MonitorRelationEnter]] = null;
  var monitorRelationList : ArrayList[MonitorRelation] = null;
  var monitorMap : MonitorMap = null;
  
  def  monitorEventList     :    ArrayList[MonitorEvent];
  
  
  var blockCount = 0L;
  
  
  def initializeContextCreateMonitorRelation()
  {
     threadId2MonitorStack = new HashMap[Long,Stack[BlockIdAndEvent4MonitorRelationEnter]]();
      monitorMap = new MonitorMap();
  }
  
  
  def newRoundCreateMonitorRelation()
  {
    monitorRelationList = new ArrayList[MonitorRelation]();
    monitorMap.newRound();
  }
  
  
}