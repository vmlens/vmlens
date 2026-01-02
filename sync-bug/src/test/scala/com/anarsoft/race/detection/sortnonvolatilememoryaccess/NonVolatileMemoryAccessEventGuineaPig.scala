package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.report.{FieldId, StaticMemoryAccessId}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val operation: Int,
                                            val runPosition: Int, val threadIndex: Int)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)

  override def staticMemoryAccessId(): StaticMemoryAccessId = new FieldId(1);

  override def loopId: Int = 0

  override def runId: Int = 0

  override def methodId: Int = 0

  override def stacktraceNode: Option[StacktraceNode] = None;

  override def runElementType(isDataRace : Boolean): ReportOperation = null;
}
