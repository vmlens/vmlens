package com.anarsoft.race.detection.processeventbytype


private trait ProcessorAndEvent[EVENT <: EventWithType[EVENT]] {

  def process(event: EVENT, algorithmForEvent: AlgorithmForEvent[EVENT]): ProcessorAndEvent[EVENT];

  def keepProcessing(): Boolean;

}
