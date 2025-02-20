package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.event.interleave.{ThreadStartEvent, VolatileAccessEvent}
import com.anarsoft.race.detection.sortutil.EventWithReadWriteContainer
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class GroupInterleaveElementBuilder {

  val arrayBuffer = new ArrayBuffer[GroupInterleaveElement]();

  def addVolatileAccessEvents(list: util.LinkedList[VolatileAccessEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[VolatileAccessEvent](
        EventArray[VolatileAccessEvent](list),
        (event: VolatileAccessEvent) => EventWithReadWriteContainer[VolatileAccessEvent](event)));
  }

  def addThreadStartEvents(list: util.LinkedList[ThreadStartEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementThreadOperationImpl[ThreadStartEvent](
        EventArray[ThreadStartEvent](list)));
  }
  
  

  def build(): List[GroupInterleaveElement] = {
    arrayBuffer.toList
  }

}
