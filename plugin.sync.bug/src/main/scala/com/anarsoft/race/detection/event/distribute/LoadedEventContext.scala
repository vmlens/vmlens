package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

trait LoadedEventContext[EVENT] {
  def addLoadedEvent(event: EVENT): Unit;

  def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit;
}
