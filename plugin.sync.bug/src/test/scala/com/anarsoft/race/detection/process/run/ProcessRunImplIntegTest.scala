package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.gen.{FieldAccessEventGen, VolatileAccessEventGen}
import com.anarsoft.race.detection.event.interleave.VolatileAccessEvent
import com.anarsoft.race.detection.event.nonvolatile.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.groupinterleave.GroupInterleaveElementBuilder
import com.anarsoft.race.detection.groupnonvolatile.GroupNonVolatileElementBuilder
import com.anarsoft.race.detection.loopAndRunData.{LoopAndRunId, RunData}
import com.vmlens.trace.agent.bootstrap.MemoryAccessType
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util


class ProcessRunImplIntegTest extends AnyFlatSpec with Matchers {

  "a read and write to/from a normal field without sync actions" should "lead to a data race" in {
    // Given
    val loopId = 0;
    val runId = 0;
    val loopIdAndRunId = LoopAndRunId(loopId, runId);

    val processRunImpl = new ProcessRunImpl();

    val nonVolatileElements = nonVolatileReadWrite(loopIdAndRunId);

    // When
    val runData = RunData.forLoopAndRun(loopIdAndRunId).copy(nonVolatileElements = nonVolatileElements);
    val runResult = processRunImpl.process(runData);

    //Then
    runResult.dataRaceCount should be(1)
  }

  "a read and write to/from a normal field separated by a read/write to a volatile field" should "not lead to a data race" in {
    // Given
    val loopId = 0;
    val runId = 0;
    val loopIdAndRunId = LoopAndRunId(loopId, runId);

    val list = new util.LinkedList[VolatileAccessEvent]();
    val fieldId = 0;
    val methodCounter = 0;
    val methodId = 0;
    val objectHashCode = 0L;

    val write = new VolatileAccessEventGen(0, 0, 0, fieldId, methodCounter,
      methodId, MemoryAccessType.IS_WRITE, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 0);
    val read = new VolatileAccessEventGen(1, 0, 1, fieldId, methodCounter,
      methodId, MemoryAccessType.IS_READ, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 3);

    list.add(read)
    list.add(write);

    val builder = new GroupInterleaveElementBuilder();
    builder.addVolatileAccessEvents(list);

    val processRunImpl = new ProcessRunImpl();

    // When
    val runData = RunData.forLoopAndRun(loopIdAndRunId).copy(nonVolatileElements = nonVolatileReadWrite(loopIdAndRunId),
      syncActionElements = builder.build());
    val runResult = processRunImpl.process(runData);

    //Then
    runResult.dataRaceCount should be(0)
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
    builder.add(list);
    builder.build();
  }


}
