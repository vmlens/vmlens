package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.FieldIdAndObjectHashcode
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationVolatileAccess

trait VolatileFieldAccessEvent extends EventWithReadWrite[VolatileFieldAccessEvent]
  with SyncActionEventWithCompareType[VolatileFieldAccessEvent]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent {

  def fieldId: Int
  def objectHashCode: Long

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.volatileAccessEvents.add(this);
  }

  def compareType(other: VolatileFieldAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }

  override def runElementType: ReportOperation = {
    new OperationVolatileAccess(new FieldIdAndObjectHashcode(fieldId, objectHashCode),operation);
  }

}
