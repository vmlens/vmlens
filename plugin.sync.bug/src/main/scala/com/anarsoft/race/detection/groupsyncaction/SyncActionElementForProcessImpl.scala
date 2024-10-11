package com.anarsoft.race.detection.groupsyncaction

import com.anarsoft.race.detection.createpartialorder.{AddToPartialOrderBuilder, PartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SyncActionElementForProcessImpl[EVENT <: SyncActionEventWithCompareType[EVENT] with WithSetStacktraceNode]
(val eventArray: EventArray[EVENT], val createContainer: (EVENT) => EventContainer[EVENT])
  extends SyncActionElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit = {
    new AddToPartialOrderBuilder(createContainer).process(eventArray, partialOrderBuilder);
  }

  def asResult(): SyncActionElementForResult = {
    new SyncActionElementForResult(eventArray);
  }
}
