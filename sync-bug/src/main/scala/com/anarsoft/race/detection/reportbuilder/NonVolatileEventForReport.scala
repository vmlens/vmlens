package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.element.MemoryAccessModifier
import com.vmlens.report.operationtextfactory.OperationTextFactory

trait NonVolatileEventForReport extends WithPosition {

  def methodId: Int;

  def staticMemoryAccessId(): StaticMemoryAccessId;

  def loopId: Int

  def runId: Int

  def stacktraceNode: Option[StacktraceNode];

  def createOperationTextFactory(memoryAccessModifier: MemoryAccessModifier): OperationTextFactory;
  
}
