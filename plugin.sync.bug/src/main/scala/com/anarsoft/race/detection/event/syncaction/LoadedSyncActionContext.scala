package com.anarsoft.race.detection.event.syncaction

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupinterleave.{GroupInterleaveElementBuilder, GroupInterleaveElementSyncActionImpl}
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
    val groupBuilder = new GroupInterleaveElementBuilder();
    groupBuilder.add(volatileAccessEvents);


    builder.addSyncActionElements(loopAndRunId, groupBuilder.build())
  }
}
