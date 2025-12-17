package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.gen.{FieldAccessEventGen, VolatileFieldAccessEventGen}
import com.anarsoft.race.detection.event.interleave.VolatileFieldAccessEvent
import com.anarsoft.race.detection.event.nonvolatile.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElementBuilder
import com.anarsoft.race.detection.rundata.{LoopAndRunId, RunData}
import com.vmlens.trace.agent.bootstrap.MemoryAccessType
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util


class DataRaceIntTest extends AnyFlatSpec with Matchers {

  "a read and write to/from a normal field without sync actions" should "lead to a data race" in {
    // Given
    val loopId = 0;
    val runId = 0;
    val loopIdAndRunId = LoopAndRunId(loopId, runId);

    val processRunImpl = new ProcessRunImpl(new ProcessRunContextBuilder().build());

    val nonVolatileElements = nonVolatileReadWrite(loopIdAndRunId);

    // When
    val runData = RunData.forLoopAndRun(loopIdAndRunId).copy(nonVolatileElements = nonVolatileElements);
    val runResult = processRunImpl.process(runData);

    //Then
    runResult.dataRaceCount should be(1)
  }

  private def nonVolatileReadWrite(loopIdAndRunId: LoopAndRunId) = {
    val fieldId = 0;
    val methodCounter = 0;
    val methodId = 0;
    val objectHashCode = 0L;

    val read = new FieldAccessEventGen(0, fieldId, methodCounter,
      MemoryAccessType.IS_READ, methodId, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 0);
    val write = new FieldAccessEventGen(1, fieldId, methodCounter,
      MemoryAccessType.IS_WRITE, methodId, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 3);

    val list = new util.LinkedList[NonVolatileFieldAccessEvent]();
    list.add(read)
    list.add(write);

    val builder = new GroupNonVolatileElementBuilder();
    builder.addFieldAccess(list);
    builder.build();
  }


}
