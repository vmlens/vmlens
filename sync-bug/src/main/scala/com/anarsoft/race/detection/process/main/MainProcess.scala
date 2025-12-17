package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.rundata.{RunData, RunResultImpl}
import com.anarsoft.race.detection.loopresult.LoopResultCollection
import com.vmlens.report.ResultForVerify
import com.anarsoft.race.detection.report.builder.{LoopResultCallbackImpl, ReportBuilder}

import java.nio.file.Path

class MainProcess(val loadDescription: LoadDescription,
                  val loadRuns: LoadRuns,
                  val processRun: ProcessRun,
                  val reportDir : Path) {
  def process() : ResultForVerify = {

    val loopResultCallback = new LoopResultCallbackImpl(); 
    
   val loopIdToResult = new ProcessEvents(loadRuns, processRun).process();
    for (elem <- loopIdToResult) {
      loopResultCallback.addRunResult(elem)
    }

    val tuple = loopResultCallback.build();
    loadDescription.load(tuple._1)
    new ReportBuilder(tuple._2, tuple._1.build(),reportDir).build();
  }
}
