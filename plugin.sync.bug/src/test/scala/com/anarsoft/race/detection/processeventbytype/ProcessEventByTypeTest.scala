package com.anarsoft.race.detection.processeventbytype

import com.anarsoft.race.detection.util.EventArray
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ProcessEventByTypeTest extends AnyFlatSpec with Matchers {

  "ProcessEventByType" should "put a new AlgorithmForOneType for each type" in {
    // Given
    val array = Array(event(0, 0), event(0, 1), event(1, 0), event(1, 1));
    val algorithmForEventMock = new AlgorithmForOneTypeFactoryMock();
    val processEventByType = new ProcessEventByType[EventWithTypeGuineaPig](algorithmForEventMock);

    // When
    processEventByType.process(new EventArray(array));

    // Then
    algorithmForEventMock.createdAlgorithm(0).processed should be(2);
    algorithmForEventMock.createdAlgorithm(1).processed should be(2);
  }

  private def event(category: Int, position: Int): EventWithTypeGuineaPig = {
    new EventWithTypeGuineaPig(category, position)
  }

}
