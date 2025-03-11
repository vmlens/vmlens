package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl
import com.anarsoft.race.detection.process.main.MainProcess
import com.anarsoft.race.detection.process.run.ProcessRunImpl
import com.anarsoft.race.detection.reportbuilder.LoopReportBuilderImpl
import com.vmlens.report.assertion.{OnDescriptionAndLeftBeforeRight, OnDescriptionAndLeftBeforeRightNoOp}
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.createreport.CreateReport

import java.io.{File, PrintStream}
import java.nio.file.{Path, Paths}

/*

C\:\\git-repo\\vmlens\\test-vmlens-maven-plugin\\target\\vmlens-agent/vmlens/
C:/report-dir/

*/
class ProcessEvents(val eventDir: Path,
                    val reportDir: Path,
                    val onTestLoopAndLeftBeforeRight : OnDescriptionAndLeftBeforeRight) {

  def process(): Unit = {

    val dir = reportDir.toFile
    if (!dir.exists()) {
      dir.mkdir();
    }

    val loadDescription = new LoadDescriptionImpl(eventDir)
    val loadRuns = new LoadRunsFactory().create(eventDir)
    val processRun = new ProcessRunImpl(onTestLoopAndLeftBeforeRight);
    val loopReportBuilder = new LoopReportBuilderImpl(new ReportBuilder());

    val mainProcess = new MainProcess(loadDescription, loadRuns, processRun, loopReportBuilder,onTestLoopAndLeftBeforeRight);
    val reportBuilder = mainProcess.process();

    val createReports = new CreateReport(reportDir);
    createReports.createReport(reportBuilder.build())

    val printStream = new PrintStream(reportDir.resolve("agentlog.txt").toFile)
    new LoadAgentLog(eventDir).load(printStream);
    printStream.close();
  }

}

object ProcessEvents {
  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    val reportDir = Paths.get(args(1));
    new ProcessEvents(dir, reportDir, new OnDescriptionAndLeftBeforeRightNoOp()).process();

  }
}