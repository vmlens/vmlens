package com.anarsoft.race.detection.sortutil.lockcontainer

import com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder.{LockEventTestBuilder , AtomicReadWriteLockEventTestBuilder}
import com.anarsoft.race.detection.event.interleave.{WithLockEnterEvent, WithLockEvent, WithLockExitEvent}
import com.vmlens.trace.agent.bootstrap.lock.LockTypes
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.WithLockEventTestBuilder

class LockContainerTest  extends AnyFlatSpec with Matchers {

  "lockContainer " should " work for lock events"  in {
    check(new LockEventTestBuilder(234L));
    
  }

  "lockContainer " should " work for AtomicReadWriteLock events" in {
    check(new AtomicReadWriteLockEventTestBuilder(234L));

  }
  
  def check(builder : WithLockEventTestBuilder): Unit = {
    val THREAD_INDEX = 3;
    
    val firstReadExit = builder.exit(LockTypes.READ_LOCK.id(), THREAD_INDEX);
    val writeExit = builder.exit(LockTypes.WRITE_LOCK.id(), THREAD_INDEX);
    val secondReadExit = builder.exit(LockTypes.READ_LOCK.id(), THREAD_INDEX);

    val readEnter = builder.enter(LockTypes.READ_LOCK.id(), THREAD_INDEX);
    val writeEnter = builder.enter(LockTypes.WRITE_LOCK.id(), THREAD_INDEX);

    var lockContainer = new LockContainer(new NoLock[WithLockEnterEvent](), new NoLock[WithLockExitEvent]());
    lockContainer = lockContainer.put(firstReadExit);


    checkOpposite(lockContainer, readEnter) should be(None)
    checkOpposite(lockContainer, writeEnter) should be(Some(firstReadExit))

    lockContainer = lockContainer.put(writeExit);

    checkOpposite(lockContainer, readEnter) should be(Some(writeExit))
    checkOpposite(lockContainer, writeEnter) should be(Some(writeExit))

    lockContainer = lockContainer.put(secondReadExit);

    checkOpposite(lockContainer, readEnter) should be(Some(writeExit))
    checkOpposite(lockContainer, writeEnter) should be(Some(secondReadExit))
  }

  def checkOpposite(lockContainer : LockContainer, checkedEvent : WithLockEvent): Option[WithLockEvent] = {
    var result : Option[WithLockEvent] = None;
    lockContainer.foreachOpposite(checkedEvent, event => result  = Some(event));
    result
  }

}
