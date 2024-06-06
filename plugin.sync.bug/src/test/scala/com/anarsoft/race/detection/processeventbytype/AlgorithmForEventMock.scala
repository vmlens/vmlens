package com.anarsoft.race.detection.processeventbytype

import scala.collection.mutable.ArrayBuffer

class AlgorithmForEventMock extends AlgorithmForEvent[EventWithTypeGuineaPig] {

  val createdAlgorithm = new ArrayBuffer[AlgorithmForOneTypeMock]()

  override def create(event: EventWithTypeGuineaPig): AlgorithmForOneType[EventWithTypeGuineaPig] = {
    val a = new AlgorithmForOneTypeMock(event.category);
    createdAlgorithm += a;
    a;
  }
}

