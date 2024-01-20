package com.anarsoft.race.detection.event.distribute

import java.util.Comparator

class CompareByLoopAndRunId extends Comparator[EventWithLoopAndRunId] {
  override def compare(o1: EventWithLoopAndRunId, o2: EventWithLoopAndRunId): Int = {
    if (o1.loopId != o2.loopId) {
      Integer.compare(o1.loopId, o2.loopId)
    } else {
      Integer.compare(o1.runId, o2.runId)
    }
  }
}
