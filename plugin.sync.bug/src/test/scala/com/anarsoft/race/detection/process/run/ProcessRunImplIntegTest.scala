package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.gen.{FieldAccessEventGen, VolatileAccessEventGen}
import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.event.syncAction.VolatileAccessEvent
import com.anarsoft.race.detection.nonvolatilememoryaccessgroup.NonVolatileMemoryAccessElementForProcessBuilder
import com.anarsoft.race.detection.process.loopAndRunData.{LoopAndRunId, RunData}
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType
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
    val runReportBuilder = new RunReportBuilderMock();

    val nonVolatileElements = nonVolatileReadWrite(loopIdAndRunId);

    // When
    val runData = RunData.forLoopAndRun(loopIdAndRunId).copy(nonVolatileMemoryAccessElements = nonVolatileElements);
    processRunImpl.process(runData, runReportBuilder);
    
    //Then
    runReportBuilder.dataRaceNonVolatileFieldAccessEventList.size should be(2)
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

    val write = new VolatileAccessEventGen(0, 0, fieldId, methodCounter,
      methodId, MemoryAccessType.IS_WRITE, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 0);
    val read = new VolatileAccessEventGen(1, 1, fieldId, methodCounter,
      methodId, MemoryAccessType.IS_READ, objectHashCode, loopIdAndRunId.loopId, loopIdAndRunId.runId, 3);

    list.add(read)
    list.add(write);

    // When


    //Then

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

    val builder = new NonVolatileMemoryAccessElementForProcessBuilder();
    builder.add(list);
    builder.build();
  }


}
