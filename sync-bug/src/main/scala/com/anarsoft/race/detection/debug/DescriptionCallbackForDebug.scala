package com.anarsoft.race.detection.debug

import com.anarsoft.race.detection.report.description.DescriptionCallback
import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadLoopOrAutomaticTestDescription}

import java.io.PrintStream

class DescriptionCallbackForDebug(stream : PrintStream) extends DescriptionCallback {
  override def addClassDescription(classDescription: ClassDescription): Unit = {
    stream.println(classDescription.toString)
  }

  override def addThreadOrLoopDescription(threadOrLoopDescription: ThreadLoopOrAutomaticTestDescription): Unit = {
    stream.println(threadOrLoopDescription.toString)
  }
}
