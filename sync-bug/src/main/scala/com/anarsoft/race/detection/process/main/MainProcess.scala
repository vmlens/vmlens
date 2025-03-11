package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopResult.LoopResultCollection
import com.anarsoft.race.detection.reportbuilder.DescriptionBuilderWrapper
import com.vmlens.report.assertion.OnDescription

class MainProcess(private val loadDescription: LoadDescription,
                  private val loadRuns: LoadRuns,
                  private val processRun: ProcessRun,
                  private val loopReportBuilder: LoopReportBuilder,
                  private val onDescription : OnDescription) {
  def process(): DescriptionBuilderForReport = {

    val loopResultCollection = new LoopResultCollection();

    for (runData <- loadRuns) {
      loopResultCollection.put(processRun.process(runData));
    }

    loopResultCollection.addToBuilder(loopReportBuilder);
    val descriptionBuilder = loopReportBuilder.build();
    loadDescription.load(new DescriptionBuilderWrapper(descriptionBuilder,onDescription))
    descriptionBuilder;
  }
}
