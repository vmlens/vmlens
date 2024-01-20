package com.anarsoft.race.detection.processEventByType

import com.anarsoft.race.detection.util.EventArray
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ProcessEventByTypeTest extends AnyFlatSpec with Matchers {

  "ProcessEventByType" should "create a new AlgorithmForOneType for each type" in {
    // Given
    val array = Array(event(0, 0), event(0, 1), event(1, 0), event(1, 1));
    val algorithmForEventMock = new AlgorithmForEventMock();
    val processEventByType = new ProcessEventByType[EventWithTypeGuineaPig](algorithmForEventMock);

    // When
    processEventByType.process(new EventArray(array));

    // Then
    algorithmForEventMock.createdAlgorithm(0).processed should be(1);
    algorithmForEventMock.createdAlgorithm(1).processed should be(1);
  }

  it should "stop processing when AlgorithmForOneType.keepProcessing return false" in {
    // Given
    val array = Array(event(0, 0), event(0, 1),
      new EventWithTypeGuineaPig(0, 2, false),
      event(0, 3), event(0, 4));
    val algorithmForEventMock = new AlgorithmForEventMock();
    val processEventByType = new ProcessEventByType[EventWithTypeGuineaPig](algorithmForEventMock);

    // When
    processEventByType.process(new EventArray(array));


    // Then
    algorithmForEventMock.createdAlgorithm(0).processed should be(2);

  }

  private def event(category: Int, position: Int): EventWithTypeGuineaPig = {
    new EventWithTypeGuineaPig(category, position, true)
  }

}
