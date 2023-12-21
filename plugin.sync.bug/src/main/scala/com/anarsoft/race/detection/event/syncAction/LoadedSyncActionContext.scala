package com.anarsoft.race.detection.event.syncAction

import com.anarsoft.race.detection.event.load.LoadedEventContext
import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunDataBuilder, LoopAndRunId}

import java.util.*

class LoadedSyncActionContext extends LoadedEventContext[LoadedSyncActionEvent] {

  val volatileAccessEvents = new ArrayList[VolatileAccessEvent]();

  def addLoadedEvent(event: LoadedSyncActionEvent): Unit = {
    event.addToContext(this);
  }

  def addVolatileAccessEvent(event: VolatileAccessEvent): Unit = {
    volatileAccessEvents.add(event);
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: LoopAndRunDataBuilder): Unit = {
    // ToDo
  }
}
