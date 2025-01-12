package com.anarsoft.race.detection.event.syncaction

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.MemoryAccessEvent
import com.vmlens.report.element.{FieldAccessTextFactory, MemoryAccessModifier, OperationTextFactory}


trait VolatileAccessEvent extends MemoryAccessEvent[VolatileAccessEvent]
  with SyncActionEventWithCompareType[VolatileAccessEvent]
  with WithSetStacktraceNode
  with LoadedSyncActionEvent {

  def fieldId: Int
  def objectHashCode: Long
  
  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.volatileAccessEvents.add(this);
  }

  def compareType(other: VolatileAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }

  override def operationTextFactory: OperationTextFactory = {
    FieldAccessTextFactory.create(MemoryAccessModifier.Volatile, operation, fieldId, objectHashCode);
  }
  
}
