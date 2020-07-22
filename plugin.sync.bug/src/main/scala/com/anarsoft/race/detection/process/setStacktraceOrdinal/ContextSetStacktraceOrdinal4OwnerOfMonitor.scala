package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.method._
import org.roaringbitmap.RoaringBitmap;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.description.ThreadNames;

trait ContextSetStacktraceOrdinal4OwnerOfMonitor extends ContextMonitor {

  def methodFlow: MethodFlow;
  def stackTraceTree: StackTraceTree;
  def threadNames: ThreadNames;
  def threadOrdinalAndStackTraceSet: HashSet[ThreadOrdinalAndStackTrace];

  var stackTraceOrdinalWithMonitor: RoaringBitmap = null;

  def stackTraceOrdinalWithMonitorOption: Option[RoaringBitmap] = Some(stackTraceOrdinalWithMonitor);
  
  
  
  
  
  def initializeContextSetStacktraceOrdinal() {
    stackTraceOrdinalWithMonitor = new RoaringBitmap();

    // parallizeId2EventList = new HashMap[Int,EventAndBlockStatementList]();

  }

}