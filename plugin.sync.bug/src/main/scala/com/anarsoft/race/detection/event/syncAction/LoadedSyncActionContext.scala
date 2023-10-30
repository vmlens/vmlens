package com.anarsoft.race.detection.event.syncAction

import com.anarsoft.race.detection.event.load.LoadedEventContext

import java.util.*

class LoadedSyncActionContext extends LoadedEventContext[LoadedSyncActionEvent] {

  val volatileAccessEventList = new ArrayList[VolatileAccessEvent]();

  def addLoadedEvent(event: LoadedSyncActionEvent): Unit = {
    event.addToContext(this);
  }

  def addVolatileAccessEvent(event: VolatileAccessEvent): Unit = {
    volatileAccessEventList.add(event);
  }

}
