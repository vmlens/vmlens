package com.anarsoft.race.detection.prozessEventByType


trait EventWithType {
  def differentType(other: EventWithType): Boolean;
}
