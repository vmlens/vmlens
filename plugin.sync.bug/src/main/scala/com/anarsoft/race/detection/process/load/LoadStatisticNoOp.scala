package com.anarsoft.race.detection.process.load

class LoadStatisticNoOp extends LoadStatistic {

  override def loadAndClose(): LoopAndRunIdToFilePosition = {
    LoopAndRunIdToFilePosition(Map());
  }
}
