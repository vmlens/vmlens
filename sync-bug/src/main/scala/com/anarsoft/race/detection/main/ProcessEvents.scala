package com.anarsoft.race.detection.main

import com.anarsoft.race.detection.process.loadAgentLog.LoadAgentLog
import com.anarsoft.race.detection.process.loadDescription.LoadDescriptionImpl
import com.anarsoft.race.detection.process.main.MainProcess
import com.anarsoft.race.detection.process.run.{ProcessRunContext, ProcessRunContextBuilder, ProcessRunImpl}
import com.anarsoft.race.detection.report.builder.LoopResultCallbackImpl
import com.vmlens.report.ResultForVerify
import com.vmlens.setup.SetupAgent.reCreate

import java.io.PrintStream
import java.nio.file.{Path, Paths}

class ProcessEvents(val eventDir: Path,
                    val reportDir: Path,
                    processRunContext : ProcessRunContext) {


  def hasThreadAndLoopDescription() : Boolean =
    new LoadDescriptionImpl(eventDir).hasThreadAndLoopDescription;

  def process(): ResultForVerify = {
    val agentLog = new LoadAgentLog(eventDir);

    if( ! agentLog.hasAgentLog()) {
      val result = new ResultForVerify();
      result.setNoAgentRun(true);
      result.setNoTestsRun(true);
      return result;
    }

    val dir = reportDir.toFile
    reCreate(dir);


    val printStream = new PrintStream(reportDir.resolve("agentlog.txt").toFile)
    agentLog.load(printStream);
    printStream.close();


    val loadDescription = new LoadDescriptionImpl(eventDir)

    if (!loadDescription.hasThreadAndLoopDescription) {
      val result = new ResultForVerify();
      result.setNoTestsRun(true);
      return result;
    }

    val loadRuns = new LoadRunsFactory().create(eventDir, loadDescription.loadDataRaceFilter());
    val processRun = new ProcessRunImpl(processRunContext);
    
    val mainProcess = new MainProcess(loadDescription, loadRuns, processRun,reportDir);
    mainProcess.process();
  }
}

object ProcessEvents {
  def main(args: Array[String]): Unit = {
    val dir = Paths.get(args(0));
    val reportDir = Paths.get(args(1));
    new ProcessEvents(dir, reportDir, new ProcessRunContextBuilder()
   //   .withShowAllRuns()
   //   .withShowAllMemoryAccess()
      .build()).
      process();

  }
}