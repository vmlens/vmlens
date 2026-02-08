package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.createautomatictest.CreateAutomaticTestFromRunData
import com.anarsoft.race.detection.createdominatortree.CreateDominatorTreeFromRunData
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
    val createDominatorTree = new CreateDominatorTreeFromRunData();
    val createAutomaticTestFromRunData = new CreateAutomaticTestFromRunData();
    
   val loopIdToResult = new ProcessEvents(loadRuns, processRun,createDominatorTree,createAutomaticTestFromRunData).process();
    for (elem <- loopIdToResult) {
      loopResultCallback.addRunResult(elem)
    }
    loopResultCallback.setAutomaticTestResult(createAutomaticTestFromRunData.build())
    
    val tuple = loopResultCallback.build();
    loadDescription.load(tuple._1)
    new ReportBuilder(tuple._2, tuple._1.build(),tuple._3,reportDir).build();
  }
}
