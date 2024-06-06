package com.anarsoft.race.detection.processeventbytype

private class ProzessorAndEventEnd[EVENT <: EventWithType[EVENT]] extends ProcessorAndEvent[EVENT] {
  override def process(event: EVENT, algorithmForEvent: AlgorithmForEvent[EVENT]): ProcessorAndEvent[EVENT] = {
    assert(false)
  }

  override def keepProcessing(): Boolean = false;
}
