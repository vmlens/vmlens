package com.anarsoft.race.detection.processeventbytype


trait WithCompareType[EVENT] {
  def compareType(other: EVENT): Int;
}
