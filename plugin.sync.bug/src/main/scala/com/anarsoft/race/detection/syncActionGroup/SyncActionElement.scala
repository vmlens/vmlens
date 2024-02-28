package com.anarsoft.race.detection.syncActionGroup

import com.anarsoft.race.detection.createPartialOrder.PartialOrderBuilder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait SyncActionElement {
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit;

  def createPartialOrder(partialOrderBuilder: PartialOrderBuilder): Unit;

  def createReport(): Unit;
}
