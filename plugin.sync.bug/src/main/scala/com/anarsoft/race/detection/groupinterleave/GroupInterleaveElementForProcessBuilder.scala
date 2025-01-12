package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.event.syncaction.VolatileAccessEvent
import com.anarsoft.race.detection.sortutil.EventContainerForMemoryAccess
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class GroupInterleaveElementForProcessBuilder {

  val arrayBuffer = new ArrayBuffer[GroupInterleaveElementForProcess]();

  def add(list: util.LinkedList[VolatileAccessEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementForProcessImpl[VolatileAccessEvent](
        EventArray[VolatileAccessEvent](list),
        (event: VolatileAccessEvent) => EventContainerForMemoryAccess[VolatileAccessEvent](event)));
  }

  def build(): List[GroupInterleaveElementForProcess] = {
    arrayBuffer.toList
  }

}
