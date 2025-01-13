package com.anarsoft.race.detection.event.nonvolatilefield

import com.anarsoft.race.detection.reportbuilder.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.vmlens.report.element.operationtextfactoryimpl.FieldAccessTextFactory
import com.vmlens.report.element.{MemoryAccessModifier, OperationTextFactory}
import com.vmlens.trace.agent.bootstrap.MemoryAccessType
import org.apache.commons.text.StringEscapeUtils

trait NonVolatileFieldAccessEvent extends LoadedNonVolatileFieldEvent
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
}
