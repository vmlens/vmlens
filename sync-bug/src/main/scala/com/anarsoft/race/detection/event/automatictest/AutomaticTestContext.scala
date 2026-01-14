package com.anarsoft.race.detection.event.automatictest

import com.anarsoft.race.detection.event.control.LoadedControlEvent
import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

import java.util

class AutomaticTestContext extends LoadedEventContext[LoadedAutomaticTestEvent] {

  private val eventList = new util.LinkedList[LoadedAutomaticTestEvent]();

  override def addLoadedEvent(event: LoadedAutomaticTestEvent): Unit = {
    eventList.add(event)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    builder.addAutomaticTestElements(loopAndRunId,eventList)
  }
}
