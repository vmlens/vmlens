package com.anarsoft.race.detection.createdominatortreeevent

class EventForSummaryOrdering[MEMORY_ACCESS_KEY <: Ordered[MEMORY_ACCESS_KEY]] extends Ordering[EventForSummary[MEMORY_ACCESS_KEY]] {

  override def compare(x: EventForSummary[MEMORY_ACCESS_KEY], y: EventForSummary[MEMORY_ACCESS_KEY]): Int = {
    if (x.threadIndex != y.threadIndex) {
      Integer.compare(x.threadIndex, y.threadIndex)
    } else if(x.dominatorTreeCounter != y.dominatorTreeCounter)  {
      Integer.compare(x.dominatorTreeCounter, y.dominatorTreeCounter)
    } else {
      x.memoryAccessKey.compare(y.memoryAccessKey)
    }
  }
  
  
}
