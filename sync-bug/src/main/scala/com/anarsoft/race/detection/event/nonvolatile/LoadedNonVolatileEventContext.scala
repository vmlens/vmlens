package com.anarsoft.race.detection.event.nonvolatile

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElementBuilder
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunDataListBuilder}

import java.util

class LoadedNonVolatileEventContext extends LoadedEventContext[LoadedNonVolatileEvent] {

  val nonVolatileFieldAccessEvents = new util.LinkedList[NonVolatileFieldAccessEvent]();
  val nonVolatileStaticFieldAccessEvents = new util.LinkedList[NonVolatileFieldAccessEventStatic]();
  val arrayAccessEvents = new util.LinkedList[ArrayAccessEvent]();

  override def addLoadedEvent(event: LoadedNonVolatileEvent): Unit = {
    event.addToContext(this)
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val groupBuilder = new GroupNonVolatileElementBuilder();
    groupBuilder.addFieldAccess(nonVolatileFieldAccessEvents)
    groupBuilder.addStaticFieldAccess(nonVolatileStaticFieldAccessEvents)
    groupBuilder.addArrayAccess(arrayAccessEvents)
    builder.addNonVolatileElements(loopAndRunId, groupBuilder.build());
  }
}
