package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.read._;
import java.util.ArrayList;
import com.anarsoft.race.detection.process.interleave.InterleaveEventList
import scala.collection.mutable.HashSet
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import scala.collection.mutable.HashSet

trait ContextMonitor {
  
  
    def  interleaveEventList : InterleaveEventList;
  
    var monitorEventStreams  :    StreamAndStreamStatistic[MonitorEvent] = null;
    var monitorEventList     :    ArrayList[MonitorEvent] = null;
    
    
  def  deadlockFilter : HashSet[StackTraceOrdinalAndMonitor];
 
        
}