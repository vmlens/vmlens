package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

class LoopAndRunIdAndContextStart[EVENT <: EventWithLoopAndRunId] extends LoopAndRunIdAndContext[EVENT] {
  override def add(event: EVENT, createContext: () => LoadedEventContext[EVENT],
                   builder: RunDataListBuilder): LoopAndRunIdAndContext[EVENT] = {
    val context = createContext();
    context.addLoadedEvent(event);
    new LoopAndRunIdAndContextProcessing[EVENT](LoopAndRunId(event.loopId, event.runId), context);
  }

  override def end(builder: RunDataListBuilder): Unit = {

  }
}
