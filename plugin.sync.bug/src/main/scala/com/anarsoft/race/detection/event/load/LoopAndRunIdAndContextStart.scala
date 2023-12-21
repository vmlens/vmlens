package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunDataBuilder, LoopAndRunId}

class LoopAndRunIdAndContextStart[EVENT <: EventWithLoopAndRunId] extends LoopAndRunIdAndContext[EVENT] {
  override def add(event: EVENT, createContext: () => LoadedEventContext[EVENT],
                   builder: LoopAndRunDataBuilder): LoopAndRunIdAndContext[EVENT] = {
    val context = createContext();
    context.addLoadedEvent(event);
    new LoopAndRunIdAndContextProcessing[EVENT](LoopAndRunId(event.loopId, event.runId), context);
  }

  override def end(builder: LoopAndRunDataBuilder): Unit = {

  }
}
