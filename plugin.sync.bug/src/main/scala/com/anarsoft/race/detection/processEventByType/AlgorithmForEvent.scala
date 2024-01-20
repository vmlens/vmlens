package com.anarsoft.race.detection.processEventByType

trait AlgorithmForEvent[EVENT] {
  def create(event: EVENT): AlgorithmForOneType[EVENT];
}
