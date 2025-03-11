package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.process.main.DescriptionBuilderForReport
import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs

class DescriptionBuilderForReportImpl(reportBuilder: ReportBuilder) extends DescriptionBuilderForReport {

  override def addClassDescription(classDescription: ClassDescription): Unit = {
    reportBuilder.addClassDescription(classDescription);
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    reportBuilder.addThreadOrLoopDescription(threadOrLoopDescription);
  }

  def build(): UILoopsAndStacktraceLeafs = {
    reportBuilder.build();
  }

}
