package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.OperationTextFactory

trait EventForRunElement {

  def threadIndex: Int

  def loopId: Int

  def runId: Int

  def runPosition: Int

  def stacktraceNode: Option[StacktraceNode];

  def operationTextFactory: OperationTextFactory;

}
