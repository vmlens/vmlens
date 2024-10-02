package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.{EventForRunElement, NonVolatileEventForReport}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.{FieldAccessTextFactory, OperationTextFactory}

private class SortedMemoryAccessListElement(var isDataRace: Boolean,
                                            val event: NonVolatileEventForReport) extends EventForRunElement {

  def runPosition: Int = event.runPosition;

  def threadIndex: Int = event.threadIndex;

  def loopId: Int = event.loopId;

  def runId: Int = event.runId;

  def stacktraceNode: Option[StacktraceNode] = event.stacktraceNode;

  def operationTextFactory: OperationTextFactory = {
    val prefix = if (isDataRace) {
      "Data Race "
    } else {
      ""
    }
    event.createOperationTextFactory(prefix);
  }

}
