package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.createautomatictest.CreateAutomaticTestFromRunData
import com.anarsoft.race.detection.createdominatortree.CreateDominatorTreeFromRunData
import com.anarsoft.race.detection.loopresult.{LoopResult, LoopResultCollection}
import com.vmlens.trace.agent.bootstrap.event.DummyLoopIds


class ProcessEvents(private val loadRuns: LoadRuns, 
                    private val processRun: ProcessRun,
                    private val createDominatorTree : CreateDominatorTreeFromRunData,
                    private val createAutomaticTestFromRunData : CreateAutomaticTestFromRunData) {
  
  def process() : Seq[LoopResult] = {

    val loopResultCollection = new LoopResultCollection(processRun.runContext.showAllRuns);
    for (runData <- loadRuns) {
      if( runData.loopAndRunId.loopId == DummyLoopIds.DUMMY_LOOP_ID ) {
        createAutomaticTestFromRunData.processDummyLoop(runData.automaticTestEvents)
      } else {
        loopResultCollection.put(processRun.process(runData),
          createDominatorTree.process(runData));
        createAutomaticTestFromRunData.process(runData)
      }
    }
    loopResultCollection.build();
  }
  
}
