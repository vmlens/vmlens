package com.anarsoft.race.detection.processeventbytype

class AlgorithmForOneTypeMock() extends AlgorithmForOneType[EventWithTypeGuineaPig] {

  var processed = 0;

  override def prozess(event: EventWithTypeGuineaPig): Unit = {
    processed = processed + 1;
  }

  override def stop(): Unit = {

  }
}
