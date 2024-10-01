package com.anarsoft.race.detection.process.load


trait LoadStatistic {

  def loadAndClose(): LoopAndRunIdToFilePosition;
}
