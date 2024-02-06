package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val isRead: Boolean,
                                            val positionInRun: Int, val threadId: Long)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)


}
