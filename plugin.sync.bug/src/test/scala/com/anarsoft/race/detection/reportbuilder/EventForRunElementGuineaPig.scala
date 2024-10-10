package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.OperationTextFactory


class EventForRunElementGuineaPig(val threadIndex: Int, val loopId: Int, val runId: Int, val runPosition: Int,
                                  val stacktraceNode: Option[StacktraceNode], val operationTextFactory: OperationTextFactory)
  extends EventForRunElement {

}
