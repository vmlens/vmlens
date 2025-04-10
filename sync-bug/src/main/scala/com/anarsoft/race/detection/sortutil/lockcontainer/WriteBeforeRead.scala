package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.event.interleave.{WithLockEvent, WithLockEventGeneric}
import com.anarsoft.race.detection.event.interleave.locktype.{LockType, ReadLock, ReentrantLock, WriteLock}


class WriteBeforeRead[EVENT <:  WithLockEventGeneric[EVENT]](val writeEvent : EVENT,
                                              val readEvent : EVENT) extends  LockContainerElement[EVENT] {

  override def update(event: EVENT): LockContainerElement[EVENT] = {
    event.lockTypeClass() match {
      case ReadLock[EVENT] () => {
           new WriteBeforeRead[EVENT] (writeEvent,event);
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
    lockType match {
      case ReadLock () => {
         f(writeEvent);
    }
      // since the read event comes after the write
      // we only have to look at the write evemt
      case ReentrantLock () => {
          f(readEvent);
    }
      case WriteLock () => {
          f(readEvent);
    }
    }
  }
  
}
