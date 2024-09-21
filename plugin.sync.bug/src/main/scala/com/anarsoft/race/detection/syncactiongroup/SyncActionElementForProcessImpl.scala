package com.anarsoft.race.detection.syncactiongroup

import com.anarsoft.race.detection.createpartialorder.{PartialOrderBuilder, SyncActionEvent}
import com.anarsoft.race.detection.setstacktrace.{EventWithStacktraceNode, SetStacktraceNodeInEvent}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SyncActionElementForProcessImpl[EVENT <: SyncActionEvent[EVENT] with EventWithStacktraceNode]
(eventArray: EventArray[EVENT]) extends SyncActionElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def createPartialOrder(partialOrderBuilder: PartialOrderBuilder): Unit = {

  }

  def addToReport(): Unit = {

  }

}
