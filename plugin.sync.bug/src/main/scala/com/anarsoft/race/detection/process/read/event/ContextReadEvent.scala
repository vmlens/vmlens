package com.anarsoft.race.detection.process.read.event

import com.anarsoft.race.detection.process.monitor._;
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.syncAction._;
import com.anarsoft.race.detection.process.nonVolatileField._;
import scala.collection.mutable.HashMap
import java.util.ArrayList
import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.scheduler._;


class ContextReadEvent {
  
  
    var methodEventStreams  :  StreamAndStreamStatistic[ApplyMethodEventVisitor] = null;
    var syncActionStreams  :   StreamAndStreamStatistic[SyncAction] = null;
    var monitorEventStreams : StreamAndStreamStatistic[MonitorEvent] = null;
    var schedulerEventStreams : StreamAndStreamStatistic[SchedulerEvent] = null;
    var fieldEventStreams : StreamAndStreamStatistic[ApplyFieldEventVisitor] = null;
    
     
    var methodId2Name : HashMap[Int,String] = null;
    var fieldId2Name : HashMap[Int,String] = null;
    
  
  
}