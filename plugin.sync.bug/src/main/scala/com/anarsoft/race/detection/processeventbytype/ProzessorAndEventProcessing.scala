package com.anarsoft.race.detection.processeventbytype

private class ProcessorAndEventProcessing[EVENT <: EventWithType[EVENT]]
(val forEvent: EVENT, val algorithmForEvent: AlgorithmForEvent[EVENT])
  extends ProcessorAndEvent[EVENT] {

  val algorithm = algorithmForEvent.create(forEvent);

  override def process(event: EVENT, algorithmForEvent: AlgorithmForEvent[EVENT]): ProcessorAndEvent[EVENT] = {
    if (forEvent.compareType(event) != 0) {
      new ProcessorAndEventProcessing[EVENT](event, algorithmForEvent)
    } else {
      if (algorithm.prozess(event)) {
        this
      } else {
        new ProzessorAndEventEnd[EVENT]();
      }

    }
  }

  override def keepProcessing(): Boolean = true;
}
