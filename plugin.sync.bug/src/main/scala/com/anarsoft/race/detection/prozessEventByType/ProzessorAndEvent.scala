package com.anarsoft.race.detection.prozessEventByType


trait ProzessorAndEvent[EVENT <: EventWithType, FAILURE] {

  def prozess(event: EVENT, newProzessor: (EVENT) => ProzessOneType[EVENT, FAILURE]): ProzessorAndEvent[EVENT, FAILURE];

  def getFailure(): Option[FAILURE];

}
