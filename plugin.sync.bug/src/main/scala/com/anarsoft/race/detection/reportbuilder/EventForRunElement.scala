package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.element.OperationTextFactory

trait EventForRunElement extends WithPosition {
  
  def loopId: Int
  def runId: Int
  def stacktraceNode: Option[StacktraceNode];
  def operationTextFactory: OperationTextFactory;

}
