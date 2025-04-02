package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.trace.agent.description.{TestLoopDescription, ThreadDescription, ThreadOrLoopDescriptionVisitor}
import com.vmlens.report.ResultForVerify

class SetLoopNameInResultForVerifyVisitor(resultForVerify : ResultForVerify) extends ThreadOrLoopDescriptionVisitor {

  override def visit(threadDescription: ThreadDescription): Unit = {
    
  }

  override def visit(whileLoopDescription: TestLoopDescription): Unit = {
    resultForVerify.setLoopName(whileLoopDescription.loopId(),whileLoopDescription.name());
  }
}
