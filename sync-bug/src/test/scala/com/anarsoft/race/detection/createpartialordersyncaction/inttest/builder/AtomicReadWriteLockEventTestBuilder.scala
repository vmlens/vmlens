package com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder

import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.WithLockEventTestBuilder
import com.anarsoft.race.detection.event.gen.{AtomicReadWriteLockEnterEventGen, AtomicReadWriteLockExitEventGen}
import com.anarsoft.race.detection.event.interleave.{AtomicNonBlockingEvent, WithLockEvent}

class AtomicReadWriteLockEventTestBuilder(val objectHashCode  : Long) extends AbstractTestBuilder with WithLockEventTestBuilder {

  def enter(lockType : Int, threadIndex : Int): WithLockEvent = {
    val pos = nextPosition(threadIndex);
    new AtomicReadWriteLockEnterEventGen(
      threadIndex,
      methodCounter,
      objectHashCode,
      lockType,
      bytecodePosition,
      methodId,
      loopId,
      runId,
      pos.runPosition,
      atomicMethodId
    )
  }
  
  def exit(lockType : Int,  threadIndex : Int) : WithLockEvent = {
    val pos = nextPosition(threadIndex);
    new AtomicReadWriteLockExitEventGen(
      threadIndex,
      methodCounter,
      objectHashCode,
      lockType,
      bytecodePosition,
      methodId,
      loopId,
      runId,
      pos.runPosition,
      atomicMethodId)
  }


}
