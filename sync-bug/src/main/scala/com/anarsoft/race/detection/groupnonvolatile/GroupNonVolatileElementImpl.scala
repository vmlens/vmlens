package com.anarsoft.race.detection.groupnonvolatile


import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.{NonVolatileMemoryAccessEvent, PartialOrder, SortNonVolatileMemoryAccess}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.assertion.EventWithAssertion

class GroupNonVolatileElementImpl[EVENT <: NonVolatileMemoryAccessEvent[EVENT] 
  with WithSetStacktraceNode]
(val eventArray: EventArray[EVENT]) extends GroupNonVolatileElement {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def sort(partialOrder: PartialOrder): GroupNonVolatileMemoryAccessElementForResult = {
    val sorted = new SortNonVolatileMemoryAccess[EVENT]().sort(eventArray, partialOrder);
    GroupNonVolatileMemoryAccessElementForResult(sorted);
  }


}
