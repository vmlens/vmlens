package com.anarsoft.race.detection.groupsyncaction

import com.anarsoft.race.detection.createpartialorder.PartialOrderBuilder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait SyncActionElementForProcess {
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit;

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit;

  def asResult(): SyncActionElementForResult;
}
