package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.reportbuilder.DescriptionBuilder
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadOrLoopDescription}

import java.io.PrintStream

class DescriptionBuilderForDebug(stream : PrintStream) extends DescriptionBuilder {
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    stream.println(classDescription.toString)
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit = {
    stream.println(threadOrLoopDescription.toString)
  }
}
