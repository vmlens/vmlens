package com.anarsoft.race.detection.process.main

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.vmlens.report.VerifyResult
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs

trait DescriptionBuilderForReport extends DescriptionBuilder {
  def build(): UILoopsAndStacktraceLeafs;
  def verifyResult() : VerifyResult
}
