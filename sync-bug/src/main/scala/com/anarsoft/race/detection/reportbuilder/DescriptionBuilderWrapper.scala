package com.anarsoft.race.detection.reportbuilder

import com.vmlens.report.assertion.OnDescription
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadOrLoopDescription}

class DescriptionBuilderWrapper(val delegate : DescriptionBuilder, val onDescription : OnDescription) extends DescriptionBuilder {
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    delegate.addClassDescription(classDescription);
    onDescription.onClassDescription(classDescription)
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    delegate.addThreadOrLoopDescription(threadOrLoopDescription)
    onDescription.onThreadOrLoopDescription(threadOrLoopDescription)
  }
}
