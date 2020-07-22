package com.anarsoft.race.detection.process.monitor

import com.anarsoft.race.detection.process.read._;
import java.util.ArrayList;
import com.anarsoft.race.detection.process.interleave.InterleaveEventList

trait ContextMonitor {
  
  
    def  interleaveEventList : InterleaveEventList;
  
    var monitorEventStreams  :    StreamAndStreamStatistic[MonitorEvent] = null;
    var monitorEventList     :    ArrayList[MonitorEvent] = null;
}