package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.loopAndRunData.RunDataListBuilder


trait LoopAndRunIdAndContext[EVENT <: EventWithLoopAndRunId] {
  def add(event: EVENT, createContext: () => LoadedEventContext[EVENT],
          builder: RunDataListBuilder): LoopAndRunIdAndContext[EVENT];

  def end(builder: RunDataListBuilder): Unit;
}
