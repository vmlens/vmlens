package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.event.interleave.{WithLockEvent, WithLockEventGeneric}
import com.anarsoft.race.detection.event.interleave.locktype.{LockType, ReadLock, ReentrantLock, WriteLock}

class ReadOnly[EVENT <: WithLockEventGeneric[EVENT]](val readEvent: EVENT) extends LockContainerElement[EVENT] {

  override def update(event: EVENT): LockContainerElement[EVENT] = {
    event.lockTypeClass() match {
      case ReadLock[EVENT] () => {
           new ReadOnly[EVENT](event);
      }
      case ReentrantLock[EVENT] () => {
          new WriteOnly[EVENT](event);
      }
      case WriteLock[EVENT] () => {
          new WriteOnly[EVENT](event);
      }
    }
  }

  override def foreach(lockType: LockType[_], f: WithLockEvent => Unit): Unit = {
    lockType match {
      case ReadLock () => {
         // Nothing to do
    }
      case ReentrantLock () => {
        f(readEvent);
    }
      case WriteLock () => {
         f(readEvent);
    }
    }
  }
}
