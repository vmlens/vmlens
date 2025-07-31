package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopResult.{LoopResult, LoopResultCollection}


class ProcessEvents(private val loadRuns: LoadRuns, private val processRun: ProcessRun) {
  
  def process() : Seq[LoopResult] = {

    val loopResultCollection = new LoopResultCollection(processRun.runContext.showAllRuns);
    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.build();
  }
  
}
