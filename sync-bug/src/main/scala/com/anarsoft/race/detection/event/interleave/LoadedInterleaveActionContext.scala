package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class LoadedInterleaveActionContext extends LoadedEventContext[LoadedInterleaveActionEvent] {

  val volatileAccessEvents = new util.LinkedList[VolatileFieldAccessEvent]();
  val staticVolatileAccessEvents = new util.LinkedList[VolatileFieldAccessEventStatic]();
  val threadStartEvents = new util.LinkedList[ThreadStartEvent]();
  val threadJoinedEvents = new util.LinkedList[ThreadJoinedEvent]();
  val monitorEvents = new util.LinkedList[MonitorEvent]()

  def addLoadedEvent(event: LoadedInterleaveActionEvent): Unit = {
    event.addToContext(this);
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val groupBuilder = new GroupInterleaveElementBuilder();
    groupBuilder.addVolatileAccessEvents(volatileAccessEvents);
    groupBuilder.addStaticVolatileAccessEvents(staticVolatileAccessEvents);
    groupBuilder.addThreadStartEvents(threadStartEvents);
    groupBuilder.addThreadJoinedEvents(threadJoinedEvents);
    groupBuilder.addMonitorEvents(monitorEvents);

    builder.addSyncActionElements(loopAndRunId, groupBuilder.build())
  }
}
