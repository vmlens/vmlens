package com.anarsoft.race.detection.processeventbytype

import scala.collection.mutable.ArrayBuffer

class AlgorithmForOneTypeFactoryMock extends AlgorithmForOneTypeFactory[EventWithTypeGuineaPig] {

  val createdAlgorithm = new ArrayBuffer[AlgorithmForOneTypeMock]()

  override def create(): AlgorithmForOneType[EventWithTypeGuineaPig] = {
    val a = new AlgorithmForOneTypeMock();
    createdAlgorithm += a;
    a;
  }
}

