package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopResult.LoopResultCollection

class ProcessEvents(private val loadRuns: LoadRuns, private val processRun: ProcessRun) {
  
  def process() : Seq[RunCountAndResult] = {

    val loopResultCollection = new LoopResultCollection();
    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.build();
  }
  

}
