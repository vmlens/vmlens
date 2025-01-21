package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.{EventForReportElement, NonVolatileEventForReport}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.MemoryAccessModifier
import com.vmlens.report.operationtextfactory.{FieldAccessTextFactory, OperationTextFactory}

private class SortedMemoryAccessListElement(var isDataRace: Boolean,
                                            val event: NonVolatileEventForReport) extends EventForReportElement {

  def methodId: Int = event.methodId;

  def runPosition: Int = event.runPosition;

  def threadIndex: Int = event.threadIndex;

  def loopId: Int = event.loopId;

  def runId: Int = event.runId;

  def stacktraceNode: Option[StacktraceNode] = event.stacktraceNode;

  def operationTextFactory: OperationTextFactory = {
    val memoryAccessModifier = if (isDataRace) {
      MemoryAccessModifier.DataRace
    } else {
      MemoryAccessModifier.NonVolatile
    }
    event.createOperationTextFactory(memoryAccessModifier);
  }

}
