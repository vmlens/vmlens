package com.anarsoft.race.detection.processEventByType


trait EventWithType[EVENT] {
  def sameType(other: EVENT): Boolean;

  def compareType(other: EVENT): Int;
}
