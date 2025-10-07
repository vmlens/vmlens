package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

class LoopAndRunIdAndContextProcessing[EVENT <: EventWithLoopAndRunId]
(val loopAndRunId: LoopAndRunId, val context: LoadedEventContext[EVENT])
  extends LoopAndRunIdAndContext[EVENT] {
  override def add(event: EVENT, createContext: () => LoadedEventContext[EVENT],
                   builder: RunDataListBuilder): LoopAndRunIdAndContext[EVENT] = {
    if (loopAndRunId.loopId == event.loopId && loopAndRunId.runId == event.runId) {
      context.addLoadedEvent(event);
      this;
    }
    else {
      context.addToBuilder(loopAndRunId, builder);
      val newContext = createContext();
      newContext.addLoadedEvent(event);
      new LoopAndRunIdAndContextProcessing[EVENT](LoopAndRunId(event.loopId, event.runId), newContext);
    }
  }

  override def end(builder: RunDataListBuilder): Unit = {
    context.addToBuilder(loopAndRunId, builder);
  }
}
