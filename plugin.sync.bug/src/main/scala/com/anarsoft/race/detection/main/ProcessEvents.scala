package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl
import com.anarsoft.race.detection.process.main.MainProcess
import com.anarsoft.race.detection.process.run.ProcessRunImpl
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.createreport.CreateReport

import java.nio.file.Paths

object ProcessEvents {
  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    val reportDir = Paths.get(args(1));

    val loadDescription = new LoadDescriptionImpl(dir)
    val loadRuns = new LoadRunsFactory().create(dir)
    val processRun = new ProcessRunImpl();
    val loopReportBuilder = new LoopReportBuilderImpl(new ReportBuilder());

    val mainProcess = new MainProcess(loadDescription, loadRuns, processRun, loopReportBuilder);
    mainProcess.process();

    val createReports = new CreateReport(reportDir);
    createReports.createReport(loopReportBuilder.build())
  }
}