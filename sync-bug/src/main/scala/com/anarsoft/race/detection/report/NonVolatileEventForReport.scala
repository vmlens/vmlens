package com.anarsoft.race.detection.report

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.WithPosition
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

trait NonVolatileEventForReport extends WithPosition {

  def methodId: Int;

  def staticMemoryAccessId(): StaticMemoryAccessId;

  def loopId: Int

  def runId: Int

  def stacktraceNode: Option[StacktraceNode];

  def runElementType(isDataRace : Boolean): ReportOperation;
  
}
