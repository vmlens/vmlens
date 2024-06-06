package com.anarsoft.race.detection.processeventbytype

trait AlgorithmForOneType[EVENT] {
  def prozess(event: EVENT): Boolean;
}
