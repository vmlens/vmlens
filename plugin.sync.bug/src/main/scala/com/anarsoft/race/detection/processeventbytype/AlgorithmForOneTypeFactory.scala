package com.anarsoft.race.detection.processeventbytype

trait AlgorithmForOneTypeFactory[EVENT] {
  def create(): AlgorithmForOneType[EVENT];
}
