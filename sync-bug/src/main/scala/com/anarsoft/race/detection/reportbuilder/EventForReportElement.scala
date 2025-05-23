package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.runelementtype.RunElementType

trait EventForReportElement extends WithPosition {

  def methodId: Int;
  def loopId: Int
  def runId: Int
  def stacktraceNode: Option[StacktraceNode];
  def runElementType: RunElementType;

}
