package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val isRead: Boolean,
                                            val runPosition: Int, val threadIndex: Int)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)

  def add(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.add(null);
  }

  def addAsDataRace(builder: RunReportForNonVolatileMemoryAccessBuilder): Unit = {
    builder.addAsDataRace(null);
  }

}
