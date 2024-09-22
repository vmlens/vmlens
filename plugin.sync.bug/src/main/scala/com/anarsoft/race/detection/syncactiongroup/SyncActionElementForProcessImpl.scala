package com.anarsoft.race.detection.syncactiongroup

import com.anarsoft.race.detection.createpartialorder.{AddToPartialOrderBuilder, PartialOrderBuilder, SyncActionEvent}
import com.anarsoft.race.detection.setstacktrace.{EventWithStacktraceNode, SetStacktraceNodeInEvent}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SyncActionElementForProcessImpl[EVENT <: SyncActionEvent[EVENT] with EventWithStacktraceNode]
(val eventArray: EventArray[EVENT],
 val createContainer: (EVENT) => EventContainer[EVENT]) extends SyncActionElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit = {
    new AddToPartialOrderBuilder(createContainer).process(eventArray, partialOrderBuilder);
  }

  def addToReport(): Unit = {

  }

}
