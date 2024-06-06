package com.anarsoft.race.detection.nonvolatilememoryaccessgroup

import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.dataView.MemoryAccessReportBuilder

trait NonVolatileMemoryAccessElement {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit;

  def sort(partialOrder: PartialOrder, sortedListBuilder: MemoryAccessReportBuilder): Unit;

}
