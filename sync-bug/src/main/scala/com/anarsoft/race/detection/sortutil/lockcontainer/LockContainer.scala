package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.event.interleave.{MonitorEvent, WithLockEnterEvent, LockEvent,  WithLockEvent, WithLockExitEvent}
import com.anarsoft.race.detection.sortutil.{EventContainer, MonitorContainer}

class LockContainer(val enter : LockContainerElement[WithLockEnterEvent],
                    val exit :  LockContainerElement[WithLockExitEvent])  extends EventContainer[WithLockEvent] {

  override def put(elem: WithLockEvent):LockContainer  =
    elem.update(this);
  
  override def foreachOpposite(elem: WithLockEvent, f: WithLockEvent => Unit): Unit =
    elem.foreachOpposite(this, f);

}

object LockContainer {

  def apply(event: LockEvent): LockContainer = {
    event.create();
  }
  
}
