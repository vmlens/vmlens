package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.process.loopAndRunData.LoopAndRunDataBuilder


trait LoopAndRunIdAndContext[EVENT <: EventWithLoopAndRunId] {
  def add(event: EVENT, createContext: () => LoadedEventContext[EVENT],
          builder: LoopAndRunDataBuilder): LoopAndRunIdAndContext[EVENT];

  def end(builder: LoopAndRunDataBuilder): Unit;
}
