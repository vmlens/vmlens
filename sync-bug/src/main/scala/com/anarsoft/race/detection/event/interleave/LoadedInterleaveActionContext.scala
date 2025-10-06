package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.distribute.LoadedEventContext
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunDataListBuilder}

import java.util

class LoadedInterleaveActionContext extends LoadedEventContext[LoadedInterleaveActionEvent] {

  val atomicNonBlockingEvents = new util.LinkedList[AtomicNonBlockingEvent]();
  val atomicReadWriteLockEvents = new util.LinkedList[WithLockEvent]();
  val volatileAccessEvents = new util.LinkedList[VolatileFieldAccessEvent]();
  val staticVolatileAccessEvents = new util.LinkedList[VolatileFieldAccessEventStatic]();
  val threadStartEvents = new util.LinkedList[ThreadStartEvent]();
  val threadJoinedEvents = new util.LinkedList[ThreadJoinedEvent]();
  val monitorEvents = new util.LinkedList[MonitorEvent]()
  val lockEvents = new util.LinkedList[WithLockEvent]()
  val barrierEvents = new util.LinkedList[BarrierEvent]()

  def addLoadedEvent(event: LoadedInterleaveActionEvent): Unit = {
    event.addToContext(this);
  }

  override def addToBuilder(loopAndRunId: LoopAndRunId, builder: RunDataListBuilder): Unit = {
    val groupBuilder = new GroupInterleaveElementBuilder();
    groupBuilder.addAtomicReadWriteLockEvents(atomicReadWriteLockEvents);
    groupBuilder.addAtomicNonBlockingEvents(atomicNonBlockingEvents);
    groupBuilder.addVolatileAccessEvents(volatileAccessEvents);
    groupBuilder.addStaticVolatileAccessEvents(staticVolatileAccessEvents);
    groupBuilder.addThreadStartEvents(threadStartEvents);
    groupBuilder.addThreadJoinedEvents(threadJoinedEvents);
    groupBuilder.addLockEvents(lockEvents);
    groupBuilder.addMonitorEvents(monitorEvents);
    groupBuilder.addBarrierEvents(barrierEvents);

    builder.addSyncActionElements(loopAndRunId, groupBuilder.build())
  }
}
