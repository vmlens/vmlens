package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.vmlens.report.runelementtype.memoryaccesskey.FieldIdAndObjectHashcode
import com.vmlens.report.runelementtype.{RunElementType, VolatileAccess}


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

  override def runElementType: RunElementType = {
    new VolatileAccess(new FieldIdAndObjectHashcode(fieldId, objectHashCode),operation);
  }

}
