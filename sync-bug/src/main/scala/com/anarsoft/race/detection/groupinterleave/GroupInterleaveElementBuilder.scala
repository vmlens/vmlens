package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.event.interleave.{MonitorContainer, MonitorEvent, ThreadJoinedEvent, ThreadStartEvent, VolatileFieldAccessEvent, VolatileFieldAccessEventStatic}
import com.anarsoft.race.detection.sortutil.EventWithReadWriteContainer
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class GroupInterleaveElementBuilder {

  val arrayBuffer = new ArrayBuffer[GroupInterleaveElement]();

  def addVolatileAccessEvents(list: util.LinkedList[VolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[VolatileFieldAccessEvent](
        EventArray[VolatileFieldAccessEvent](list),
        (event: VolatileFieldAccessEvent) => EventWithReadWriteContainer[VolatileFieldAccessEvent](event)));
  }

  def addStaticVolatileAccessEvents(list: util.LinkedList[VolatileFieldAccessEventStatic]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[VolatileFieldAccessEventStatic](
        EventArray[VolatileFieldAccessEventStatic](list),
        (event: VolatileFieldAccessEventStatic) => EventWithReadWriteContainer[VolatileFieldAccessEventStatic](event)));
  }

  def addThreadStartEvents(list: util.LinkedList[ThreadStartEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementThreadOperationImpl[ThreadStartEvent](
        EventArray[ThreadStartEvent](list)));
  }

  def addThreadJoinedEvents(list: util.LinkedList[ThreadJoinedEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementThreadOperationImpl[ThreadJoinedEvent](
        EventArray[ThreadJoinedEvent](list)));
  }

  def addMonitorEvents(list: util.LinkedList[MonitorEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[MonitorEvent](
        EventArray[MonitorEvent](list),
        (event: MonitorEvent) => MonitorContainer(event)));
  }
  
  

  def build(): List[GroupInterleaveElement] = {
    arrayBuffer.toList
  }

}
