package com.anarsoft.race.detection.prozessEventByType

import com.anarsoft.race.detection.prozessEventByType.EventWithType
import com.anarsoft.race.detection.util.EventArray

class ProzessEventByType[EVENT <: EventWithType, FAILURE](val newProzessor: (EVENT) => ProzessOneType[EVENT, FAILURE]) {

  def prozess(array: EventArray[EVENT]): Option[FAILURE] = {
    val iter = array.iterator();
    var prozessorAndEvent: ProzessorAndEvent[EVENT, FAILURE] = new ProzessorAndEventStart[EVENT, FAILURE]();
    while (iter.hasNext && prozessorAndEvent.getFailure().isEmpty) {
      val event = iter.next();
      prozessorAndEvent = prozessorAndEvent.prozess(event, newProzessor);
    }
    prozessorAndEvent.getFailure();
  }

}
