package com.anarsoft.race.detection.groupsyncaction

import com.anarsoft.race.detection.event.syncAction.VolatileAccessEvent
import com.anarsoft.race.detection.sortutil.EventContainerForMemoryAccess
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class SyncActionElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[SyncActionElementForProcess]();

  def add(list: util.LinkedList[VolatileAccessEvent]): Unit = {
    arrayBuffer.append(
      new SyncActionElementForProcessImpl[VolatileAccessEvent](
        EventArray[VolatileAccessEvent](list),
        (event: VolatileAccessEvent) => EventContainerForMemoryAccess[VolatileAccessEvent](event)));
  }

  def build(): List[SyncActionElementForProcess] = {
    arrayBuffer.toList
  }

}
