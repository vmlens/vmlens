package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopResult.LoopResultCollection

class MainProcess(private val loadDescription: LoadDescription,
                  private val loadRuns: LoadRuns,
                  private val processRun: ProcessRun,
                  private val loopReportBuilder: LoopReportBuilder) {
  def process(): DescriptionBuilderForReport = {

    val loopResultCollection = new LoopResultCollection();

    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.addToBuilder(loopReportBuilder);
    val descriptionBuilder = loopReportBuilder.build();
    loadDescription.load(descriptionBuilder);
    descriptionBuilder;
  }
}
