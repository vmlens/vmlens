package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.createstacktrace.MethodEvent
import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}
import com.anarsoft.race.detection.util.EventArray

import java.util

class LoadedMethodEventContext extends LoadedEventContext[LoadedMethodEvent] {

  private[this] val eventList = new util.LinkedList[MethodEvent]();

  override def addLoadedEvent(event: LoadedMethodEvent): Unit = {
    eventList.add(event)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    builder.addMethodEvents(loopAndRunId, eventList)
  }
}
