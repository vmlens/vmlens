package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.trace.agent.description.{ClassDescription, ThreadOrLoopDescription}

trait DescriptionBuilder {

  def addClassDescription(classDescription: ClassDescription): Unit;

  def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit;

}
