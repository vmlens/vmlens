package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeContext
import com.anarsoft.race.detection.event.interleave.locktype.{LockType, LockTypeBuilder}
import com.anarsoft.race.detection.sortutil.lockcontainer.{LockContainer, NoLock}
import com.vmlens.trace.agent.bootstrap.lock.LockTypes

trait WithLockEnterEvent extends WithLockEventGeneric[WithLockEnterEvent] {
  
  def asEither(): Either[WithLockEnterEvent, WithLockExitEvent] = Left(this);

  def create(): LockContainer = new LockContainer(lockTypeClass().create(this),new NoLock[WithLockExitEvent]());

  def update(lockContainer: LockContainer): LockContainer =
    new LockContainer(lockContainer.enter.update(this),lockContainer.exit);

  def foreachOpposite(lockContainer: LockContainer, f: WithLockEvent => Unit): Unit =
    lockContainer.exit.foreach(lockTypeClass(),f);

  override def add(context: CreateDominatorTreeContext): Unit = {
    context.stack.lockEnter( lockTypeClass().reportLockType() , context.objectHashCodeToInt.get(objectHashCode));
  }
}
