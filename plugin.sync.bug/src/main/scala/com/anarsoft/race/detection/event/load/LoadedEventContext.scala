package com.anarsoft.race.detection.event.load

trait LoadedEventContext[EVENT] {
  def addLoadedEvent(event: EVENT): Unit;
}
