package com.anarsoft.race.detection.prozessEventByType

trait ProzessOneType[EVENT, FAILURE] {
  def prozess(event: EVENT): Option[FAILURE];
}
