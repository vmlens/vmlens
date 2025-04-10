package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.event.interleave.{WithLockEvent, WithLockEventGeneric}
import com.anarsoft.race.detection.event.interleave.locktype.{LockType, ReadLock, ReentrantLock, WriteLock}

class NoLock[EVENT <:  WithLockEventGeneric[EVENT]] extends LockContainerElement[EVENT] {

  override def update(event: EVENT): LockContainerElement[EVENT] = {
    event.lockTypeClass() match {
      case ReadLock[EVENT] () => {
    new ReadOnly[EVENT] (event);
    }
      case ReentrantLock[EVENT] () => {
    new WriteOnly[EVENT] (event);
    }
      case WriteLock[EVENT] () => {
    new WriteOnly[EVENT] (event);
    }
    }
  }

  override def foreach(lockType: LockType[_], f: WithLockEvent => Unit): Unit = {
    // Nothing to do
  }
  
}


