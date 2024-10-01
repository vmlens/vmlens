package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}

class DescriptionBuilderForDebug extends DescriptionBuilder {
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    println(classDescription)
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    println(threadOrLoopDescription)
  }
}
