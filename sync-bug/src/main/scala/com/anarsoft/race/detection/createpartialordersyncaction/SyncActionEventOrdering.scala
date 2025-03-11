package com.anarsoft.race.detection.createpartialordersyncaction

class SyncActionEventOrdering[EVENT <: SyncActionEventWithCompareType[EVENT]] extends Ordering[EVENT] {
  override def compare(x: EVENT, y: EVENT): Int = {
    if (x.compareType(y) != 0) {
      x.compareType(y)
    } else {
      x.runPosition.compare(y.runPosition);
    }
  }
}
