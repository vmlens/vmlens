package com.anarsoft.race.detection.process.interleave

import java.util.ArrayList;
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.model.scheduler.LoopId2Result
import scala.collection.mutable.HashMap
import  com.anarsoft.race.detection.model.result.StackTraceOrdinalAndMonitor
import scala.collection.mutable.HashSet

trait ContextInterleave {
  
  var interleaveEventList : InterleaveEventList = null;
  var loopId2Result : LoopId2Result = null;
  

 // var loopName2RunCount : HashMap[String,Int] = null;
  
  def loopId2Name : HashMap[Int,String] 
  
  def initializeContextInterleave()
  {
    interleaveEventList = new InterleaveEventList();
  }
  
  
}