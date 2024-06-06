package com.anarsoft.race.detection.processeventbytype

import com.anarsoft.race.detection.processeventbytype.EventWithType
import com.anarsoft.race.detection.util.EventArray

class ProcessEventByType[EVENT <: EventWithType[EVENT]](val algorithmForEvent: AlgorithmForEvent[EVENT]) {

  def process(array: EventArray[EVENT]) = {
    val iter = array.iterator();
    var prozessorAndEvent: ProcessorAndEvent[EVENT] = new ProcessorAndEventStart[EVENT]();
    while (iter.hasNext && prozessorAndEvent.keepProcessing()) {
      val event = iter.next();
      prozessorAndEvent = prozessorAndEvent.process(event, algorithmForEvent);
    }
  }
}
