package com.anarsoft.race.detection.processEventByType


trait EventWithType[EVENT] {
  def sameType(other: EVENT): Boolean;
}
