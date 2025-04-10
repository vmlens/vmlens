package com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder

import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.ReadOrWriteEventBuilder
import com.anarsoft.race.detection.event.gen.AtomicNonBlockingEventGen
import com.anarsoft.race.detection.event.interleave.AtomicNonBlockingEvent
import com.vmlens.trace.agent.bootstrap.MemoryAccessType

class AtomicNonBlockingTestBuilder(val objectHashCode  : Long)
  extends AbstractTestBuilder with ReadOrWriteEventBuilder[AtomicNonBlockingEvent]{

  def read(threadIndex : Int): AtomicNonBlockingEvent =  event(threadIndex,MemoryAccessType.IS_READ)

  def write(threadIndex : Int): AtomicNonBlockingEvent = event(threadIndex,MemoryAccessType.IS_WRITE)

  private def event( threadIndex : Int, operation : Int) : AtomicNonBlockingEvent = {
    val pos = nextPosition(threadIndex);
    new AtomicNonBlockingEventGen(
      threadIndex,
      bytecodePosition,
      methodCounter,
      methodId,
      operation,
      objectHashCode,
      loopId,
      runId,
      pos.runPosition,
      atomicMethodId
    )
  }
  
}