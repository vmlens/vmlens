package com.anarsoft.race.detection.process.run

import com.anarsoft.race.detection.event.gen.FieldAccessEventGen
import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import java.util


class ProcessRunImplIntegTest extends AnyFlatSpec with Matchers {

  "a read and write to/from a normal field without sync actions" should "lead to a data race" in {
    // Given
    val threadIndex = 0;
    val fieldId = 0;
    val methodCounter = 0;
    val methodId = 0;
    val objectHashCode = 0L;
    val loopId = 0;
    val runId = 0;

    val read = new FieldAccessEventGen(threadIndex, fieldId, methodCounter,
      MemoryAccessType.IS_READ, methodId, objectHashCode, loopId, runId, 0);
    val write = new FieldAccessEventGen(threadIndex, fieldId, methodCounter,
      MemoryAccessType.IS_WRITE, methodId, objectHashCode, loopId, runId, 3);

    val list = new util.LinkedList[NonVolatileFieldAccessEvent]();
    list.add(read)
    list.add(write);

    // When


    //Then

  }

  "a read and write to/from a normal field separated by a read/write to a volatile field" should "not lead to a data race" in {
    // Given


    // When


    //Then

  }


}
