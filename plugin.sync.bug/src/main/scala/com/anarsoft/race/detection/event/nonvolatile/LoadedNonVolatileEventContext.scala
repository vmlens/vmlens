package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElementBuilder
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class LoadedNonVolatileEventContext extends LoadedEventContext[LoadedNonVolatileEvent] {

  val nonVolatileAccessEvents = new util.LinkedList[NonVolatileFieldAccessEvent]();

  override def addLoadedEvent(event: LoadedNonVolatileEvent): Unit = {
    event.addToContext(this)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val groupBuilder = new GroupNonVolatileElementBuilder();
    groupBuilder.add(nonVolatileAccessEvents)
    builder.addNonVolatileElements(loopAndRunId, groupBuilder.build());
  }
}
