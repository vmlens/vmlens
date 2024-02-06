package com.anarsoft.race.detection.processEventByType


trait EventWithType[EVENT] {
  def compareType(other: EVENT): Int;
}
