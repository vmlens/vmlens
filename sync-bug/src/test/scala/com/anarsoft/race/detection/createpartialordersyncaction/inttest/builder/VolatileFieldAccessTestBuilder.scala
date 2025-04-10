package com.anarsoft.race.detection.createpartialordersyncaction.inttest.builder

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.createpartialordersyncaction.inttest.generic.ReadOrWriteEventBuilder
import com.anarsoft.race.detection.event.gen.VolatileFieldAccessEventGen
import com.anarsoft.race.detection.event.interleave.VolatileFieldAccessEvent
import com.vmlens.trace.agent.bootstrap.MemoryAccessType

class VolatileFieldAccessTestBuilder(val fieldId  : Int, val objectHashCode  : Long) 
     extends AbstractTestBuilder with ReadOrWriteEventBuilder[VolatileFieldAccessEvent]{

  def read(threadIndex : Int): VolatileFieldAccessEvent =  event(threadIndex,MemoryAccessType.IS_READ)

  def write(threadIndex : Int): VolatileFieldAccessEvent = event(threadIndex,MemoryAccessType.IS_WRITE)

  private def event( threadIndex : Int, operation : Int) : VolatileFieldAccessEvent = {
    val pos = nextPosition(threadIndex);
    new VolatileFieldAccessEventGen(threadIndex,
      bytecodePosition,
      fieldId,
      methodCounter,
      methodId,
      operation,
      objectHashCode,
      loopId,
       runId,
      pos.runPosition
    )
  }

}
