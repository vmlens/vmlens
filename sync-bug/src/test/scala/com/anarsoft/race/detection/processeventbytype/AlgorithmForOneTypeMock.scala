package com.anarsoft.race.detection.processeventbytype

class AlgorithmForOneTypeMock() extends AlgorithmForOneType[WithCompareTypeGuineaPig] {

  var processed = 0;

  override def prozess(event: WithCompareTypeGuineaPig): Unit = {
    processed = processed + 1;
  }

  override def stop(): Unit = {

  }
}
