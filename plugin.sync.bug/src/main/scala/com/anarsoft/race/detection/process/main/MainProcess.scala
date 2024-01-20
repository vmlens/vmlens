package com.anarsoft.race.detection.process.main

class MainProcess(val loadDescription: LoadDescription,
                  val processDescription: ProcessDescription,
                  val loadEvents: LoadEvents,
                  val processRun: ProcessRun,
                  val reportBuilder: ReportBuilder) {
  def process(): Unit = {
    val descriptionData = loadDescription.load();
    val descriptionBuilder = processDescription.process(descriptionData);
    for (runData <- loadEvents) {
      val runReportBuilder = processRun.process(runData);
      reportBuilder.add(runReportBuilder);
    }
    reportBuilder.build();
  }

}
