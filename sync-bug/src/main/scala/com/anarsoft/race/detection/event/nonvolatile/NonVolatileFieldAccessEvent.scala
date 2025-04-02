package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.reportbuilder.FieldId
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.vmlens.report.runelementtype.memoryaccesskey.FieldIdAndObjectHashcode
import com.vmlens.report.runelementtype.{NonVolatileAccess, RunElementType}

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

  override def runElementType(isDataRace : Boolean): RunElementType = {
   new NonVolatileAccess(new FieldIdAndObjectHashcode(fieldId, objectHashCode), operation, isDataRace);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.nonVolatileFieldAccessEvents.add(this)
  }
  
}
