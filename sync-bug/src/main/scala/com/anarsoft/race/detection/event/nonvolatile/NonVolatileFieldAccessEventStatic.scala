package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.report.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationNonVolatileAccess

trait NonVolatileFieldAccessEventStatic extends LoadedNonVolatileEvent with
  NonVolatileMemoryAccessEvent[NonVolatileFieldAccessEventStatic]
  with WithSetStacktraceNode {

  def fieldId: Int

  def compareType(other: NonVolatileFieldAccessEventStatic): Int = {
    fieldId.compareTo(other.fieldId)
  }

  def staticMemoryAccessId() = new FieldId(fieldId);

  override def runElementType(isDataRace : Boolean): ReportOperation = {
    new OperationNonVolatileAccess(new com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.FieldId(fieldId), operation, isDataRace);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.nonVolatileStaticFieldAccessEvents.add(this)
  }

  override def take(filteredFieldIds: Set[Int]): Boolean = !filteredFieldIds.contains(fieldId);

}
