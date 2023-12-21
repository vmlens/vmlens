package com.anarsoft.race.detection.event.load


import com.anarsoft.race.detection.process.loopAndRunData.LoopAndRunDataBuilder

import java.util.Collections.sort
import java.util.*

/**
 * Distribute the events based on the loop and run id and then on the type
 * Add them for each run to the run context to create Groups.
 */
class DistributeEvents[EVENT <: EventWithLoopAndRunId] {

  def distribute(events: List[EVENT], createContext: () => LoadedEventContext[EVENT], builder: LoopAndRunDataBuilder): Unit = {
    var current: LoopAndRunIdAndContext[EVENT] = new LoopAndRunIdAndContextStart[EVENT]();
    sort(events, new CompareByLoopAndRunId());
    val iter = events.iterator();
    while (iter.hasNext()) {
      current = current.add(iter.next(), createContext, builder);
    }
    current.end(builder);
  }
}
