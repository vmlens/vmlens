package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.process.main.UILoopsAndStacktraceLeafsBuilder
import com.vmlens.report.ResultForVerify
import com.vmlens.report.builder.ReportBuilder
import com.vmlens.report.uielement.UILoopsAndStacktraceLeafs
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadOrLoopDescription}

class UILoopsAndStacktraceLeafsBuilderImpl(reportBuilder: ReportBuilder, resultForVerify : ResultForVerify) extends UILoopsAndStacktraceLeafsBuilder {
  
  private val visitor = new SetLoopNameInResultForVerifyVisitor(resultForVerify);
  
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    reportBuilder.addClassDescription(classDescription);
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    reportBuilder.addThreadOrLoopDescription(threadOrLoopDescription);
    threadOrLoopDescription.accept(visitor);
  }

  def build(): UILoopsAndStacktraceLeafs = {
    reportBuilder.build();
  }
  
  def buildResultForVerify() : ResultForVerify = resultForVerify;

}
