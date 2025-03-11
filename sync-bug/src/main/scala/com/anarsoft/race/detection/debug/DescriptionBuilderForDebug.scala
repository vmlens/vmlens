package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}
import com.vmlens.report.assertion.DebugLogger

class DescriptionBuilderForDebug(val debugLogger : DebugLogger) extends DescriptionBuilder {
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    debugLogger.println(classDescription.toString)
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    debugLogger.println(threadOrLoopDescription.toString)
  }
}
