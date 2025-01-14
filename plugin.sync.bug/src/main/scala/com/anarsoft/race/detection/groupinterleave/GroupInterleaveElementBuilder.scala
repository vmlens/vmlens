package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.event.interleave.VolatileAccessEvent
import com.anarsoft.race.detection.sortutil.EventContainerForMemoryAccess
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class GroupInterleaveElementBuilder {

  val arrayBuffer = new ArrayBuffer[GroupInterleaveElement]();

  def add(list: util.LinkedList[VolatileAccessEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[VolatileAccessEvent](
        EventArray[VolatileAccessEvent](list),
        (event: VolatileAccessEvent) => EventContainerForMemoryAccess[VolatileAccessEvent](event)));
  }

  def build(): List[GroupInterleaveElement] = {
    arrayBuffer.toList
  }

}
