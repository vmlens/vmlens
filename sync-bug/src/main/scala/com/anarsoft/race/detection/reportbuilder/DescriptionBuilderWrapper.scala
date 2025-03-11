package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}
import com.vmlens.report.assertion.OnDescription

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
