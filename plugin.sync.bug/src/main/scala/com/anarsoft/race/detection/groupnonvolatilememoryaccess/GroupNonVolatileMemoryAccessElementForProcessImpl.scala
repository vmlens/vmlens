package com.anarsoft.race.detection.groupnonvolatilememoryaccess


import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.{NonVolatileMemoryAccessEvent, PartialOrder, SortNonVolatileMemoryAccess}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class GroupNonVolatileMemoryAccessElementForProcessImpl[EVENT <: NonVolatileMemoryAccessEvent[EVENT] with WithSetStacktraceNode]
(eventArray: EventArray[EVENT]) extends GroupNonVolatileMemoryAccessElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def sort(partialOrder: PartialOrder): GroupNonVolatileMemoryAccessElementForResult = {
    val sorted = new SortNonVolatileMemoryAccess[EVENT]().sort(eventArray, partialOrder);
    GroupNonVolatileMemoryAccessElementForResult(sorted);
  }


}
