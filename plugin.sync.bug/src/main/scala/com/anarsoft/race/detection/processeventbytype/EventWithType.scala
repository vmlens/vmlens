package com.anarsoft.race.detection.processeventbytype


trait EventWithType[EVENT] {
  def compareType(other: EVENT): Int;
}
