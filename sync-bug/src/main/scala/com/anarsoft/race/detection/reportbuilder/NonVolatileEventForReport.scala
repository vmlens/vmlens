package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.vmlens.report.runelementtype.RunElementType

trait NonVolatileEventForReport extends WithPosition {

  def methodId: Int;

  def staticMemoryAccessId(): StaticMemoryAccessId;

  def loopId: Int

  def runId: Int

  def stacktraceNode: Option[StacktraceNode];

  def runElementType(isDataRace : Boolean): RunElementType;
  
}
