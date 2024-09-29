package com.anarsoft.race.detection.nonvolatilememoryaccessgroup

import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import com.anarsoft.race.detection.setstacktrace.{EventWithStacktraceNode, SetStacktraceNodeInEvent}
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.{NonVolatileMemoryAccessEvent, PartialOrder, SortNonVolatileMemoryAccess}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class NonVolatileMemoryAccessElementForProcessImpl[EVENT <: NonVolatileMemoryAccessEvent[EVENT] with EventWithStacktraceNode]
(eventArray: EventArray[EVENT]) extends NonVolatileMemoryAccessElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def sort(partialOrder: PartialOrder): NonVolatileMemoryAccessElementForResult = {
    val sorted = new SortNonVolatileMemoryAccess[EVENT]().sort(eventArray, partialOrder);
    NonVolatileMemoryAccessElementForResult(sorted);
  }


}
