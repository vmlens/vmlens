package com.anarsoft.race.detection.event.syncAction

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupsyncaction.SyncActionElementForProcessImpl
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}
import com.anarsoft.race.detection.sortutil.EventContainerForMemoryAccess
import com.anarsoft.race.detection.util.EventArray

import java.util

class LoadedSyncActionContext extends LoadedEventContext[LoadedSyncActionEvent] {

  val volatileAccessEvents = new util.LinkedList[VolatileAccessEvent]();
  val threadStartEvents = new util.LinkedList[ThreadStartEvent]();

  def addLoadedEvent(event: LoadedSyncActionEvent): Unit = {
    event.addToContext(this);
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val elements = List(new SyncActionElementForProcessImpl[VolatileAccessEvent](
      EventArray[VolatileAccessEvent](volatileAccessEvents),
      event => EventContainerForMemoryAccess(event)
    ))
    builder.addSyncActionElements(loopAndRunId, elements)
  }
}
