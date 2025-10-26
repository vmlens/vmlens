package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.reportbuilder.ArrayObjectHashCode
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.NonVolatileMemoryAccessEvent
import com.vmlens.report.runelementtype.{NonVolatileAccess, RunElementType}
import com.vmlens.report.runelementtype.memoryaccesskey.ArrayObjectHashCodeAndIndex

trait ArrayAccessEvent  extends LoadedNonVolatileEvent
with NonVolatileMemoryAccessEvent[ArrayAccessEvent]
with WithSetStacktraceNode {

  def arrayIndex: Int
  def objectHashCode: Long

  def compareType(other: ArrayAccessEvent): Int = {
    if (objectHashCode != other.objectHashCode) {
      objectHashCode.compareTo(other.objectHashCode)
    } else {
      arrayIndex.compareTo(other.arrayIndex)
    }
  }

  def staticMemoryAccessId() = new ArrayObjectHashCode(objectHashCode);

  override def runElementType(isDataRace : Boolean): RunElementType = {
    new NonVolatileAccess(new ArrayObjectHashCodeAndIndex(objectHashCode,arrayIndex), operation, isDataRace);
  }

  override def addToContext(context: LoadedNonVolatileEventContext): Unit = {
    context.arrayAccessEvents.add(this)
  }

  override def take(filteredFieldIds: Set[Int]): Boolean = true;
}
