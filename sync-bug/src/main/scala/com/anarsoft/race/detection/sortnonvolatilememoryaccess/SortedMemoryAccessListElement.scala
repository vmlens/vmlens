package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.report.{EventForReportElement, NonVolatileEventForReport}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

private class SortedMemoryAccessListElement(var isDataRace: Boolean,
                                            val event: NonVolatileEventForReport) extends EventForReportElement {

  def methodId: Int = event.methodId;

  def runPosition: Int = event.runPosition;

  def threadIndex: Int = event.threadIndex;

  def loopId: Int = event.loopId;

  def runId: Int = event.runId;

  def stacktraceNode: Option[StacktraceNode] = event.stacktraceNode;

  def runElementType: ReportOperation = event.runElementType(isDataRace);
  

}
