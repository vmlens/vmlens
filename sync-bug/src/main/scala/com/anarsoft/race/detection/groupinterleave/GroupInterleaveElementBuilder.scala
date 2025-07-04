package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.event.interleave.{AtomicNonBlockingEvent, BarrierEvent, LockEvent, MonitorEvent, ThreadJoinedEvent, ThreadStartEvent, VolatileFieldAccessEvent, VolatileFieldAccessEventStatic, WithLockEvent}
import com.anarsoft.race.detection.sortutil.lockcontainer.LockContainer
import com.anarsoft.race.detection.sortutil.{EventContainer, EventWithReadWriteContainer, MonitorContainer}
import com.anarsoft.race.detection.util.EventArray

import java.util
import scala.collection.mutable.ArrayBuffer


class GroupInterleaveElementBuilder {
  
  val arrayBuffer = new ArrayBuffer[GroupInterleaveElement]();

  def addAtomicNonBlockingEvents(list: util.LinkedList[AtomicNonBlockingEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[AtomicNonBlockingEvent](
        EventArray[AtomicNonBlockingEvent](list),
        GroupInterleaveElementBuilder.create_container_atomic_non_blocking));
  }

  def addAtomicReadWriteLockEvents(list: util.LinkedList[WithLockEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[WithLockEvent](
        EventArray[WithLockEvent](list), GroupInterleaveElementBuilder.create_container_lock));
  }
  
  def addVolatileAccessEvents(list: util.LinkedList[VolatileFieldAccessEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[VolatileFieldAccessEvent](
        EventArray[VolatileFieldAccessEvent](list),
        GroupInterleaveElementBuilder.create_container_volatile_field));
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

  def addLockEvents(list: util.LinkedList[WithLockEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[WithLockEvent](
        EventArray[WithLockEvent](list),GroupInterleaveElementBuilder.create_container_lock));
  }
  
  def addMonitorEvents(list: util.LinkedList[MonitorEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[MonitorEvent](
        EventArray[MonitorEvent](list),
        (event: MonitorEvent) => MonitorContainer(event)));
  }

  def addBarrierEvents(list: util.LinkedList[BarrierEvent]): Unit = {
    arrayBuffer.append(
      new GroupInterleaveElementSyncActionImpl[BarrierEvent](
        EventArray[BarrierEvent](list),
        (event: BarrierEvent) => event.create()));
  }
  
  def build(): List[GroupInterleaveElement] = {
    arrayBuffer.toList
  }

}      
      
object  GroupInterleaveElementBuilder {

  val create_container_volatile_field: VolatileFieldAccessEvent => EventContainer[VolatileFieldAccessEvent] =
    (event: VolatileFieldAccessEvent) => {
      EventWithReadWriteContainer[VolatileFieldAccessEvent](event)
    };

  val create_container_atomic_non_blocking : AtomicNonBlockingEvent => EventContainer[AtomicNonBlockingEvent] =
    (event: AtomicNonBlockingEvent) => {
      EventWithReadWriteContainer[AtomicNonBlockingEvent](event)
    };

    val create_container_lock: WithLockEvent => LockContainer = 
      (event: WithLockEvent) =>  event.create();
  
}
