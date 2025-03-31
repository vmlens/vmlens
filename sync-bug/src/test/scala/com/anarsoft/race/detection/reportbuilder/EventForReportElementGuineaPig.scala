package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.runelementtype.RunElementType


class EventForReportElementGuineaPig(val threadIndex: Int, val loopId: Int, val runId: Int, val runPosition: Int,
                                     val stacktraceNode: Option[StacktraceNode], val runElementType: RunElementType)
  extends EventForReportElement {
  override def methodId: Int = 0
}
