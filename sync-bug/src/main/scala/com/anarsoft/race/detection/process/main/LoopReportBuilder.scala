package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder


trait LoopReportBuilder {

  def addRunResult(runResult: RunCountAndResult): Unit

  def build(): UILoopsAndStacktraceLeafsBuilder

}
