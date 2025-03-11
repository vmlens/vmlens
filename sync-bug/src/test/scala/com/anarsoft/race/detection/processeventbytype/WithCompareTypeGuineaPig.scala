package com.anarsoft.race.detection.processeventbytype

class WithCompareTypeGuineaPig(val category: Int, val position: Int)
  extends WithCompareType[WithCompareTypeGuineaPig] {

  override def compareType(other: WithCompareTypeGuineaPig): Int = category.compare(other.category);
}
