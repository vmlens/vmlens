package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.{EventForReportElement, NonVolatileEventForReport}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.operationtextfactoryimpl.FieldAccessTextFactory
import com.vmlens.report.element.{MemoryAccessModifier, OperationTextFactory}

private class SortedMemoryAccessListElement(var isDataRace: Boolean,
                                            val event: NonVolatileEventForReport) extends EventForReportElement {

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
