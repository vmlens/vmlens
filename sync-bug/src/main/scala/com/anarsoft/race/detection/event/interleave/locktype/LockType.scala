package com.anarsoft.race.detection.event.interleave.locktype

import com.anarsoft.race.detection.event.interleave.{WithLockEvent, WithLockEventGeneric}
import com.anarsoft.race.detection.sortutil.lockcontainer.{LockContainerElement, ReadOnly, WriteOnly}
import com.vmlens.report.runelementtype.ReportLockType

sealed trait LockType[EVENT <:  WithLockEventGeneric[EVENT]] {

  def create(lockEvent : EVENT) : LockContainerElement[EVENT];
  def reportLockType() : ReportLockType;

}

case class ReadLock[EVENT <: WithLockEventGeneric[EVENT]]() extends LockType[EVENT] {

  override def create(lockEvent : EVENT) : LockContainerElement[EVENT] = new ReadOnly[EVENT](lockEvent);

  override def reportLockType(): ReportLockType = ReportLockType.READ_LOCK;
  
}

case class ReentrantLock[EVENT <: WithLockEventGeneric[EVENT]]() extends LockType[EVENT]  {

  override def create(lockEvent: EVENT): LockContainerElement[EVENT] = new WriteOnly[EVENT](lockEvent);

  override def reportLockType(): ReportLockType = ReportLockType.REENTRANT_LOCK;
  
}

case class WriteLock[EVENT <: WithLockEventGeneric[EVENT]]()  extends LockType[EVENT]  {

  override def create(lockEvent: EVENT): LockContainerElement[EVENT] = new WriteOnly[EVENT](lockEvent);

  override def reportLockType(): ReportLockType = ReportLockType.WRITE_LOCK;
  
}


