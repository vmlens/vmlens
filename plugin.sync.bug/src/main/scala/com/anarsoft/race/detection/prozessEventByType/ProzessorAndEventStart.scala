package com.anarsoft.race.detection.prozessEventByType


class ProzessorAndEventStart[EVENT <: EventWithType, FAILURE] extends ProzessorAndEvent[EVENT, FAILURE] {

  def prozess(event: EVENT, newProzessor: (EVENT) => ProzessOneType[EVENT, FAILURE]): ProzessorAndEvent[EVENT, FAILURE] = {
    this;
  }

  def getFailure(): Option[FAILURE] = None;
}
