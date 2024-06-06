package com.anarsoft.race.detection.nonvolatilememoryaccessgroup

import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.dataView.MemoryAccessReportBuilder

class NonVolatileMemoryAccessGroup(val elementList: List[NonVolatileMemoryAccessElement]) {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    elementList.foreach(elem => elem.setStacktraceNode(threadIdToStacktraceNodeArray))
  }

  def sort(partialOrder: PartialOrder, sortedListBuilder: MemoryAccessReportBuilder): Unit = {
    elementList.foreach(elem => elem.sort(partialOrder, sortedListBuilder));
  }

}
