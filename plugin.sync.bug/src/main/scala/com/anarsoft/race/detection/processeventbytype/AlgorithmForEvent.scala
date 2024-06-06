package com.anarsoft.race.detection.processeventbytype

trait AlgorithmForEvent[EVENT] {
  def create(event: EVENT): AlgorithmForOneType[EVENT];
}
