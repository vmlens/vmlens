package com.anarsoft.race.detection.report.builder

import com.anarsoft.race.detection.automatictest.IdToAutomaticTest
import com.anarsoft.race.detection.loopresult.LoopResult


trait LoopResultCallback {

  def addRunResult(runResult: LoopResult): Unit
  def setAutomaticTestResult(idToAutomaticTest : IdToAutomaticTest) : Unit;
  
}
