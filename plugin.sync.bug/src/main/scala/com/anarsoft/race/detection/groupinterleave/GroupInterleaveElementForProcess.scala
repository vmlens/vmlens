package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createpartialordersyncaction.PartialOrderBuilder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupInterleaveElementForProcess {
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit;

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit;

  def asResult(): GroupInterleaveElementForResult;
}
