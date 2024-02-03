package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.vmlens.report.eventView.MemoryAccessView
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable.ArrayBuffer

class SortedMemoryAccessListTest extends AnyFlatSpec with Matchers {

  private class MemoryAccessReportBuilderMock extends MemoryAccessReportBuilder {
    val dataRaceList = new ArrayBuffer[Boolean]();

    override def addMemoryAccess(event: MemoryAccessView): Unit = {
      dataRaceList.append(false);
    }

    override def addDataRace(event: MemoryAccessView): Unit = {
      dataRaceList.append(true);
    }
  }

  "SortedMemoryAccessList" should "swt a data race in both memory access events" in {
    // Given
    val memoryAccessReportBuilder = new MemoryAccessReportBuilderMock();
    val memoryAccessEventBuilder = new MemoryAccessEventBuilder();
    val sortedMemoryAccessList = new SortedMemoryAccessList[NonVolatileMemoryAccessEventGuineaPig]()

    // When
    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());
    memoryAccessEventBuilder.incrementPositionInRun(5);

    val firstDataRaceElement = memoryAccessEventBuilder.read();
    sortedMemoryAccessList.add(firstDataRaceElement);

    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());
    memoryAccessEventBuilder.incrementPositionInRun(12);
    sortedMemoryAccessList.addDataRace(firstDataRaceElement, memoryAccessEventBuilder.read());
    sortedMemoryAccessList.add(memoryAccessEventBuilder.read());

    sortedMemoryAccessList.buildResult(memoryAccessReportBuilder)

    // Then
    memoryAccessReportBuilder.dataRaceList.toList should be(List(false, true, false, true, false));
  }
}
