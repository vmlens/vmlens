package com.anarsoft.race.detection.processEventByType

trait AlgorithmForOneType[EVENT] {
  def prozess(event: EVENT): Boolean;
}
