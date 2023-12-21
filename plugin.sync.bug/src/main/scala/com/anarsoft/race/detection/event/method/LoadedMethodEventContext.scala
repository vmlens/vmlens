package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.event.load.LoadedEventContext
import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunDataBuilder, LoopAndRunId}
import com.anarsoft.race.detection.stacktrace.MethodEvent
import com.anarsoft.race.detection.util.EventArray

import java.util

class LoadedMethodEventContext extends LoadedEventContext[LoadedMethodEvent] {

  private[this] val eventList = new util.ArrayList[MethodEvent]();

  override def addLoadedEvent(event: LoadedMethodEvent): Unit = {
    eventList.add(event)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: LoopAndRunDataBuilder): Unit = {
    builder.add(loopAndRunId, new EventArray[MethodEvent](eventList.toArray(Array.ofDim[MethodEvent](0))))
  }
}
