package com.anarsoft.race.detection.createstacktrace

class MethodEventOrdering extends Ordering[MethodEvent] {

  override def compare(x: MethodEvent, y: MethodEvent): Int = {
    if (x.threadIndex != y.threadIndex) {
      Integer.compare(x.threadIndex, y.threadIndex)
    } else {
      Integer.compare(x.methodCounter, y.methodCounter)
    }
  }

}
