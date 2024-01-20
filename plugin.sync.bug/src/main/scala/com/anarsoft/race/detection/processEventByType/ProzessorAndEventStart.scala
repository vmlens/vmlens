package com.anarsoft.race.detection.processEventByType


private class ProcessorAndEventStart[EVENT <: EventWithType[EVENT]] extends ProcessorAndEvent[EVENT] {
  override def process(event: EVENT, algorithmForEvent: AlgorithmForEvent[EVENT]): ProcessorAndEvent[EVENT] = {
    new ProcessorAndEventProcessing[EVENT](event, algorithmForEvent);
  }

  override def keepProcessing(): Boolean = true;
}
