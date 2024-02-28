package com.anarsoft.race.detection.nonVolatileMemoryAccessGroup

import com.anarsoft.race.detection.setStacktrace.{EventWithStacktraceNode, SetStacktraceNodeInEvent}
import com.anarsoft.race.detection.sortNonVolatileMemoryAccess.{NonVolatileMemoryAccessEvent, PartialOrder, SortNonVolatileMemoryAccess}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.dataView.MemoryAccessReportBuilder

class NonVolatileMemoryAccessElementImpl[EVENT <: NonVolatileMemoryAccessEvent[EVENT] with EventWithStacktraceNode]
(eventArray: EventArray[EVENT]) extends NonVolatileMemoryAccessElement {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def sort(partialOrder: PartialOrder, sortedListBuilder: MemoryAccessReportBuilder): Unit = {
    new SortNonVolatileMemoryAccess[EVENT]().sort(eventArray, partialOrder, sortedListBuilder);
  }

}
