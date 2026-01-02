package com.anarsoft.race.detection.report

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

trait EventForReportElement extends WithPosition {

  def methodId: Int;
  def loopId: Int
  def runId: Int
  def stacktraceNode: Option[StacktraceNode];
  def runElementType: ReportOperation;

}
