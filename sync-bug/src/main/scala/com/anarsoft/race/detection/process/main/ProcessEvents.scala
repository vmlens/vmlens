package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.createdominatortree.CreateDominatorTreeFromRunData
import com.anarsoft.race.detection.loopresult.{LoopResult, LoopResultCollection}


class ProcessEvents(private val loadRuns: LoadRuns, 
                    private val processRun: ProcessRun,
                    private val createDominatorTree : CreateDominatorTreeFromRunData) {
  
  def process() : Seq[LoopResult] = {

    val loopResultCollection = new LoopResultCollection(processRun.runContext.showAllRuns);
    for (runData <- loadRuns) {
      
      loopResultCollection.put(processRun.process(runData),createDominatorTree.process(runData));
    }

    loopResultCollection.build();
  }
  
}
