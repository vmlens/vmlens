package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createstacktrace.WithMethodCounter

class CreateDominatorTreeEventOrdering extends Ordering[WithMethodCounter] {

  override def compare(x: WithMethodCounter, y: WithMethodCounter): Int = {
    if (x.threadIndex != y.threadIndex) {
      Integer.compare(x.threadIndex, y.threadIndex)
    } else {
      Integer.compare(x.methodCounter, y.methodCounter)
    }
  }


}
