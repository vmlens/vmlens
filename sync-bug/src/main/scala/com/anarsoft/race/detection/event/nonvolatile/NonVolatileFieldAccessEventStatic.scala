package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.reportbuilder.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.vmlens.report.input.run.{NonVolatileAccess, RunElementType}

trait NonVolatileFieldAccessEventStatic extends LoadedNonVolatileEvent with
  NonVolatileMemoryAccessEvent[NonVolatileFieldAccessEventStatic]
  with WithSetStacktraceNode {

  def fieldId: Int

  def compareType(other: NonVolatileFieldAccessEventStatic): Int = {
    fieldId.compareTo(other.fieldId)
  }

  def staticMemoryAccessId() = new FieldId(fieldId);

  override def runElementType(isDataRace : Boolean): RunElementType = {
    new NonVolatileAccess(new com.vmlens.report.input.run.memoryaccesskey.FieldId(fieldId), operation, isDataRace);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.nonVolatileStaticFieldAccessEvents.add(this)
  }

  override def take(filteredFieldIds: Set[Int]): Boolean = !filteredFieldIds.contains(fieldId);

}
