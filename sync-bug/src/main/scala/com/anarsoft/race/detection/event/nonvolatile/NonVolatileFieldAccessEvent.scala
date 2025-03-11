package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.reportbuilder.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.vmlens.report.assertion.{EventWithAssertion, OnDescriptionAndEvent}
import com.vmlens.report.element.MemoryAccessModifier
import com.vmlens.report.operationtextfactory.{FieldAccessTextFactory, OperationTextFactory}

trait NonVolatileFieldAccessEvent extends LoadedNonVolatileEvent
  with NonVolatileMemoryAccessEvent[NonVolatileFieldAccessEvent]
  with WithSetStacktraceNode {

  def fieldId: Int
  def objectHashCode: Long

  def compareType(other: NonVolatileFieldAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      fieldId.compareTo(other.fieldId)
    }
  }

  def staticMemoryAccessId() = new FieldId(fieldId);

  override def createOperationTextFactory(memoryAccessModifier: MemoryAccessModifier): OperationTextFactory = {
    FieldAccessTextFactory.create(memoryAccessModifier, operation, fieldId, objectHashCode);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.nonVolatileAccessEvents.add(this)
  }
  
}
