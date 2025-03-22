package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.process.main.DescriptionBuilderForReport
import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}
import com.vmlens.report.VerifyResult
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs

class DescriptionBuilderForReportImpl(reportBuilder: ReportBuilder) extends DescriptionBuilderForReport {

  private var failureCount = 0;
  private var dataRaceCount = 0;

  def setFailures(i : Int): Unit = {
    failureCount =   i;
  }
  
  def setDataRaces(i : Int): Unit = {
    dataRaceCount =   i;
  }
  


  override def addClassDescription(classDescription: ClassDescription): Unit = {
    reportBuilder.addClassDescription(classDescription);
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    reportBuilder.addThreadOrLoopDescription(threadOrLoopDescription);
  }

  def build(): UILoopsAndStacktraceLeafs = {
    reportBuilder.build();
  }
  
  def verifyResult() : VerifyResult = new VerifyResult(failureCount,dataRaceCount);

}
