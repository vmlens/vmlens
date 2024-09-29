package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.{FieldId, RunReportForNonVolatileMemoryAccessBuilder, StaticMemoryAccessId}

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val operation: Int,
                                            val runPosition: Int, val threadIndex: Int)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)

  override def staticMemoryAccessId(): StaticMemoryAccessId = new FieldId(1);

  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.add(null);
  }

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.addAsDataRace(null);
  }
}
