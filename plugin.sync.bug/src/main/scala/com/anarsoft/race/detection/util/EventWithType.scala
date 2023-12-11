package com.anarsoft.race.detection.util

trait EventWithType extends ThreadIdAndMethodCounter {
  def differentType(other: EventWithType): Boolean;
}
