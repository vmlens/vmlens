package com.anarsoft.race.detection.sortnonvolatilememoryaccess

private class NonVolatileMemoryAccessEventOrdering[EVENT <: NonVolatileMemoryAccessEvent[EVENT]] extends Ordering[EVENT] {
  override def compare(x: EVENT, y: EVENT): Int = {
    if (x.compareType(y) != 0) {
      x.compareType(y)
    } else {
      x.runPosition.compare(y.runPosition);
    }
  }
}
