package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

class NonVolatileMemoryAccessEventGuineaPig(val typeId: Int, val isRead: Boolean,
                                            val positionInRun: Int, val threadId: Long)
  extends NonVolatileMemoryAccessEvent[NonVolatileMemoryAccessEventGuineaPig] {
  override def isLeftOnly(): Boolean = isRead

  override def isRightOnly(): Boolean = !isRead

  override def sameType(other: NonVolatileMemoryAccessEventGuineaPig): Boolean = typeId == other.typeId

  override def compareType(other: NonVolatileMemoryAccessEventGuineaPig): Int = typeId.compareTo(other.typeId)


}
