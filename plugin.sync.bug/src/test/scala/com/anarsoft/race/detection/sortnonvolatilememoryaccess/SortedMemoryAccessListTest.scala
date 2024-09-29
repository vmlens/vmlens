package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.event.nonVolatileField.NonVolatileFieldAccessEvent
import com.anarsoft.race.detection.reportbuilder.RunReportForNonVolatileMemoryAccessBuilder
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class SortedMemoryAccessListTest extends AnyFlatSpec with Matchers {

  private class RunReportForNonVolatileMemoryAccessBuilderMock extends RunReportForNonVolatileMemoryAccessBuilder {
    val dataRaceList = new ArrayBuffer[Boolean]();

    override def add(event: NonVolatileFieldAccessEvent): Unit = {
      dataRaceList.append(false);
    }

    override def addAsDataRace(event: NonVolatileFieldAccessEvent): Unit = {
      dataRaceList.append(true);
    }
  }

  "SortedMemoryAccessList" should "swt a data race in both memory access events" in {
    // Given
    val memoryAccessReportBuilder = new RunReportForNonVolatileMemoryAccessBuilderMock();
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder();
    val sortedMemoryAccessList = new SortedMemoryAccessList()

    // When
    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());
    memoryAccessEventBuilder.incrementPositionInRun(5);

    val firstDataRaceElement = memoryAccessEventBuilder.read();
    sortedMemoryAccessList.add(firstDataRaceElement);

    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());
    memoryAccessEventBuilder.incrementPositionInRun(12);
    sortedMemoryAccessList.setDataRace(firstDataRaceElement);
    sortedMemoryAccessList.addDataRace(memoryAccessEventBuilder.read());

    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());

    sortedMemoryAccessList.buildResult(memoryAccessReportBuilder)

    // Then
    memoryAccessReportBuilder.dataRaceList.toList should be(List(false, true, false, true, false));
  }
}
