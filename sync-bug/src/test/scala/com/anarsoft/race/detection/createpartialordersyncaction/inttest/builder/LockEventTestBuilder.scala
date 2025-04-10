package com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder

import com.anarsoft.race.detection.event.gen.{LockEnterEventGen, LockExitEventGen}
import com.anarsoft.race.detection.event.interleave.{AtomicNonBlockingEvent, WithLockEvent}
import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.WithLockEventTestBuilder

class LockEventTestBuilder(val objectHashCode  : Long) extends AbstractTestBuilder with WithLockEventTestBuilder {

  def enter(lockType : Int, threadIndex : Int): WithLockEvent = {
    val pos = nextPosition(threadIndex);
    new LockEnterEventGen(
      threadIndex,
      methodCounter,
      objectHashCode,
      lockType,
      bytecodePosition,
      methodId,
      loopId,
      runId,
      pos.runPosition)
  }


  def exit(lockType : Int,  threadIndex : Int) : WithLockEvent = {
    val pos = nextPosition(threadIndex);
    new LockExitEventGen(
      threadIndex,
      methodCounter,
      objectHashCode,
      lockType,
      bytecodePosition,
      methodId,
      loopId,
      runId,
      pos.runPosition)
  }


}
