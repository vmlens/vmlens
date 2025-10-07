package com.anarsoft.race.detection.event.control

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

import scala.collection.mutable.ArrayBuffer

class LoadedControlContext extends LoadedEventContext[LoadedControlEvent] {

  private val controlEvents = new ArrayBuffer[ControlEvent]();

  override def addLoadedEvent(event: LoadedControlEvent): Unit = {
    controlEvents.append(event)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    builder.addControlEvents(loopAndRunId, controlEvents.toList)
  }
}
