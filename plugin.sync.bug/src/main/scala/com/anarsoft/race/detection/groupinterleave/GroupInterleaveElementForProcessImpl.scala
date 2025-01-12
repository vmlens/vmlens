package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createpartialordersyncaction.{AddToPartialOrderBuilder, PartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementForProcessImpl[EVENT <: SyncActionEventWithCompareType[EVENT] with WithSetStacktraceNode]
(val eventArray: EventArray[EVENT], val createContainer: (EVENT) => EventContainer[EVENT])
  extends GroupInterleaveElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit = {
    new AddToPartialOrderBuilder(createContainer).process(eventArray, partialOrderBuilder);
  }

  def asResult(): GroupInterleaveElementForResult = {
    new GroupInterleaveElementForResult(eventArray);
  }
}
