package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunDataBuilder, LoopAndRunId}

trait LoadedEventContext[EVENT] {
  def addLoadedEvent(event: EVENT): Unit;

  def addToBuilder(loopAndRunId: LoopAndRunId, builder: LoopAndRunDataBuilder): Unit;
}
