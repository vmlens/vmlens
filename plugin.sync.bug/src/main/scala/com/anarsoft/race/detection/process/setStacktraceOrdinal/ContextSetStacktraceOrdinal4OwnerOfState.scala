package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.method._
import org.roaringbitmap.RoaringBitmap;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.description.ThreadNames;
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.process.state._
import java.util.ArrayList;



trait ContextSetStacktraceOrdinal4OwnerOfState  {
    def methodFlow: MethodFlow;
  def stackTraceTree: StackTraceTree;
  def threadNames: ThreadNames;
  def threadOrdinalAndStackTraceSet: HashSet[ThreadOrdinalAndStackTrace];



  def stackTraceOrdinalWithMonitorOption: Option[RoaringBitmap] = None;
  
  
  def stateStaticFieldEventList : ArrayList[StateEventStaticField]
  def stateFieldEventList : ArrayList[StateEventField];
  def  stateArrayEventList : ArrayList[StateEventArray];
  
  
  
  
}