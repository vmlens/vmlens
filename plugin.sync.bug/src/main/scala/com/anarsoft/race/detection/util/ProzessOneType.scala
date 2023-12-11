package com.anarsoft.race.detection.util

trait ProzessOneType[EVENT, FAILURE] {
  def prozess(event: EVENT): Option[FAILURE];
}
