package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.EventWithReadWrite
import com.vmlens.report.assertion.{EventWithAssertion, OnDescriptionAndEvent}
import com.vmlens.report.element.MemoryAccessModifier
import com.vmlens.report.operationtextfactory.{FieldAccessTextFactory, OperationTextFactory}


trait VolatileFieldAccessEvent extends EventWithReadWrite[VolatileFieldAccessEvent]
  with SyncActionEventWithCompareType[VolatileFieldAccessEvent]
  with WithSetStacktraceNode
  with LoadedInterleaveActionEvent 
  with EventWithAssertion {

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

  override def operationTextFactory: OperationTextFactory = {
    FieldAccessTextFactory.create(MemoryAccessModifier.Volatile, operation, fieldId, objectHashCode);
  }

  override def add(onDescriptionAndEvent: OnDescriptionAndEvent): Unit = {
    onDescriptionAndEvent.onVolatileAccessEvent(loopId,fieldId,threadIndex,operation)
  }
}
