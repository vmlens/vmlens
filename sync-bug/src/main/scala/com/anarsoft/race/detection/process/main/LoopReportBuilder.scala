package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.loopResult.LoopResult
import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder


trait LoopReportBuilder {

  def addRunResult(runResult: LoopResult): Unit

  def build(): UILoopsAndStacktraceLeafsBuilder

}
