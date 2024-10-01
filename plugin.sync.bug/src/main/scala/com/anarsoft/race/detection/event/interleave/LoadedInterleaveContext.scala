package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import scala.collection.mutable.ArrayBuffer

class LoadedInterleaveContext extends LoadedEventContext[LoadedInterleaveEvent] {

  private val interleaveEvents = new ArrayBuffer[InterleaveEvent]();

  override def addLoadedEvent(event: LoadedInterleaveEvent): Unit = {
    interleaveEvents.append(event)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    builder.addInterleaveEvents(loopAndRunId, interleaveEvents.toList)
  }
}
