package com.anarsoft.race.detection.createlastthreadposition

import com.anarsoft.race.detection.util.WithPosition

class PositionOrdering extends Ordering[WithPosition] {

  override def compare(x: WithPosition, y: WithPosition): Int = {
    if(x.threadIndex != y.threadIndex) {
      Integer.compare(x.threadIndex,y.threadIndex)
    } else {
      Integer.compare(x.runPosition, y.runPosition)
    }
  }
}
