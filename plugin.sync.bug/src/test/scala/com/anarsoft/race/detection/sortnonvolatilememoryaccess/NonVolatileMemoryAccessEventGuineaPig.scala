package com.anarsoft.race.detection.sortnonvolatilememoryaccess

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val isRead: Boolean,
                                            val runPosition: Int, val threadIndex: Int)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)


}
