package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.vmlens.report.ResultForVerify
import com.vmlens.report.output.UILoopsAndStacktraceLeafs

trait UILoopsAndStacktraceLeafsBuilder extends DescriptionBuilder {
  def build(): UILoopsAndStacktraceLeafs;
  def buildResultForVerify() : ResultForVerify
}
