package com.anarsoft.race.detection.process.scheduler

import com.anarsoft.race.detection.process.interleave.InterleaveEventList
import com.anarsoft.race.detection.process.read._;
import scala.collection.mutable.HashMap
import java.util.ArrayList
import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap


trait ContextSchedulerRead {
  
  
    def atomicMethodEventList : ArrayList[XMethodEvent];
    def initializeContextSetStacktraceOrdinal();
    
    
     def methodId2Ordinal :  ModelKey2OrdinalMap[Int]; 
  
    def interleaveEventList : InterleaveEventList;
    var schedulerEventStreams  :  StreamAndStreamStatistic[SchedulerEvent]  = null;
  
  
}