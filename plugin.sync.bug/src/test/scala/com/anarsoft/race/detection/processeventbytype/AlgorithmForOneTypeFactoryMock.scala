package com.anarsoft.race.detection.processeventbytype

import scala.collection.mutable.ArrayBuffer

class AlgorithmForOneTypeFactoryMock extends AlgorithmForOneTypeFactory[WithCompareTypeGuineaPig] {

  val createdAlgorithm = new ArrayBuffer[AlgorithmForOneTypeMock]()

  override def create(): AlgorithmForOneType[WithCompareTypeGuineaPig] = {
    val a = new AlgorithmForOneTypeMock();
    createdAlgorithm += a;
    a;
  }
}

