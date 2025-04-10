package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.lockcontainer.{LockContainer, NoLock}

trait WithLockExitEvent extends WithLockEventGeneric[WithLockExitEvent] {

  def asEither(): Either[WithLockEnterEvent, WithLockExitEvent] = Right(this);

  def create(): LockContainer = new LockContainer(new NoLock[WithLockEnterEvent](),lockTypeClass().create(this));

  def update(lockContainer: LockContainer): LockContainer =
     new LockContainer(lockContainer.enter , lockContainer.exit.update(this));

  def foreachOpposite(lockContainer: LockContainer, f: WithLockEvent => Unit): Unit = {
    // as exit mus t always be done before an enter can be executed
    // enter followed by an exit n an different thread can not happen
  }


}
