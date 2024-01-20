package com.anarsoft.race.detection.processEventByType

class AlgorithmForOneTypeMock(val expectedCategory: Int) extends AlgorithmForOneType[EventWithTypeGuineaPig] {


  var processed = 0;

  override def prozess(event: EventWithTypeGuineaPig): Boolean = {
    assert(event.category == expectedCategory)
    processed = processed + 1;
    event.keepProcessing;
  }


}
