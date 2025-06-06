package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.ResultForVerify
import com.vmlens.trace.agent.bootstrap.description.{TestLoopDescription, ThreadDescription, ThreadOrLoopDescriptionVisitor}

class SetLoopNameInResultForVerifyVisitor(resultForVerify : ResultForVerify) extends ThreadOrLoopDescriptionVisitor {

  override def visit(threadDescription: ThreadDescription): Unit = {
    
  }

  override def visit(whileLoopDescription: TestLoopDescription): Unit = {
    resultForVerify.setLoopName(whileLoopDescription.loopId(),whileLoopDescription.name());
  }
}
