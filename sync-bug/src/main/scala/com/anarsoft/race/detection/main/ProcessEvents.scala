package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl
import com.anarsoft.race.detection.process.main.MainProcess
import com.anarsoft.race.detection.process.run.{ProcessRunContext, ProcessRunContextBuilder, ProcessRunImpl}
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl
import com.vmlens.report.ResultForVerify
import com.vmlens.report.assertion.{OnDescriptionAndLeftBeforeRight, OnDescriptionAndLeftBeforeRightNoOp, OnEvent, OnEventNoOp}
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.createreport.{CreateHtmlReport, CreateTxtReport}
import com.vmlens.setup.SetupAgent.reCreate

import java.io.PrintStream
import java.nio.file.{Path, Paths}

class ProcessEvents(val eventDir: Path,
                    val reportDir: Path,
                    processRunContext : ProcessRunContext) {

  def process(): ResultForVerify = {
    val dir = reportDir.toFile
    reCreate(dir);


    val printStream = new PrintStream(reportDir.resolve("agentlog.txt").toFile)
    new LoadAgentLog(eventDir).load(printStream);
    printStream.close();


    val loadDescription = new LoadDescriptionImpl(eventDir)
    val loadRuns = new LoadRunsFactory().create(eventDir)
    val processRun = new ProcessRunImpl(processRunContext);
    val loopReportBuilder = new LoopReportBuilderImpl(new ReportBuilder());

    val mainProcess = new MainProcess(loadDescription, loadRuns, processRun, loopReportBuilder,
      processRunContext.onTestLoopAndLeftBeforeRight);
    val reportBuilder = mainProcess.process();

    val createReports = if(processRunContext.txtFormat) {
      new CreateTxtReport(reportDir)
    } else {
      new CreateHtmlReport(reportDir)
    }
    createReports.createReport(reportBuilder.build())

    reportBuilder.buildResultForVerify();
  }
}

object ProcessEvents {
  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    val reportDir = Paths.get(args(1));
    new ProcessEvents(dir, reportDir, new ProcessRunContextBuilder().
      build()).
      process();

  }
}