package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createstacktrace.WithMethodCounter

class CreateDominatorTreeEventOrdering extends Ordering[CreateDominatorTreeEvent] {

  override def compare(x: CreateDominatorTreeEvent, y: CreateDominatorTreeEvent): Int = {
    if (x.threadIndex != y.threadIndex) {
      Integer.compare(x.threadIndex, y.threadIndex)
    } else {
      Integer.compare(x.dominatorTreeCounter, y.dominatorTreeCounter)
    }
  }


}
