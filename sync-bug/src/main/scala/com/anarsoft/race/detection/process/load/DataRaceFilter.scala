package com.anarsoft.race.detection.process.load

import com.anarsoft.race.detection.event.nonvolatile.LoadedNonVolatileEvent

class DataRaceFilter(val filterMap :  Map[Int, Set[Int]]) extends EventFilter[LoadedNonVolatileEvent] {

  override def take(event: LoadedNonVolatileEvent): Boolean = {
    filterMap.get(event.loopId) match {
      case  None => {
      true;
    } case Some(set) => {
        event.take(set);
      }
    }
  }
}
