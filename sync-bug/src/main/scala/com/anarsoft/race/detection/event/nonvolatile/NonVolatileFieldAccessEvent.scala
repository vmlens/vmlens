package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.report.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.FieldIdAndObjectHashcode
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationNonVolatileAccess
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

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

  override def runElementType(isDataRace : Boolean): ReportOperation = {
   new OperationNonVolatileAccess(new FieldIdAndObjectHashcode(fieldId, objectHashCode), operation, isDataRace);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.nonVolatileFieldAccessEvents.add(this)
  }

  override def take(filteredFieldIds: Set[Int]): Boolean = ! filteredFieldIds.contains(fieldId);
  
}
