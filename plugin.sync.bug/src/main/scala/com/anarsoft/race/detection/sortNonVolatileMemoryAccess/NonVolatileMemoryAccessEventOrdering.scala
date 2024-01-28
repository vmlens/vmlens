package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

private class NonVolatileMemoryAccessEventOrdering[EVENT <: NonVolatileMemoryAccessEvent[EVENT]] extends Ordering[EVENT] {
  override def compare(x: EVENT, y: EVENT): Int = {
    if (!x.sameType(y)) {
      x.compareType(y)
    } else {
      x.positionInRun.compare(y.positionInRun);
    }
  }
}
