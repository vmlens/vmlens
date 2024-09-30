package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopAndRunData.RunData
import com.anarsoft.race.detection.loopResult.LoopResultCollection
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl

class MainProcess(val loadDescription: LoadDescription,
                  val loadRuns: LoadRuns,
                  val processRun: ProcessRun,
                  val loopReportBuilder: LoopReportBuilder) {
  def process(): Unit = {

    val loopResultCollection = new LoopResultCollection();

    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.addToBuilder(loopReportBuilder);
    loadDescription.load(loopReportBuilder);
    loopReportBuilder.build();

  }
}
