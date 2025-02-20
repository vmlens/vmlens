package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class LoadedInterleaveActionContext extends LoadedEventContext[LoadedInterleaveActionEvent] {

  val volatileAccessEvents = new util.LinkedList[VolatileAccessEvent]();
  val threadStartEvents = new util.LinkedList[ThreadStartEvent]();

  def addLoadedEvent(event: LoadedInterleaveActionEvent): Unit = {
    event.addToContext(this);
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val groupBuilder = new GroupInterleaveElementBuilder();
    groupBuilder.addVolatileAccessEvents(volatileAccessEvents);
    groupBuilder.addThreadStartEvents(threadStartEvents);

    builder.addSyncActionElements(loopAndRunId, groupBuilder.build())
  }
}
