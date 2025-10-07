package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.rundata.RunDataListBuilder

import java.util.*
import java.util.Collections.sort

/**
 * Distribute the events based on the loop and run id and then on the type
 * Add them for each run to the run context to put Groups.
 */
class DistributeEvents[EVENT <: EventWithLoopAndRunId](val createContext: () => LoadedEventContext[EVENT]) {

  def distribute(events: List[EVENT], builder: RunDataListBuilder): Unit = {
    var current: LoopAndRunIdAndContext[EVENT] = new LoopAndRunIdAndContextStart[EVENT]();
    sort(events, new CompareByLoopAndRunId());
    val iter = events.iterator();
    while (iter.hasNext()) {
      current = current.add(iter.next(), createContext, builder);
    }
    current.end(builder);
  }
}
