package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.event.interleave.{WithLockEvent, WithLockEventGeneric}
import com.anarsoft.race.detection.event.interleave.locktype.LockType

trait LockContainerElement[EVENT <: WithLockEventGeneric[EVENT]] {

  def update(event : EVENT) : LockContainerElement[EVENT];
  def foreach(lockType : LockType[_], f : (WithLockEvent) => Unit )  : Unit;

}
