package com.anarsoft.race.detection.reportbuilder

import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadOrLoopDescription}

trait DescriptionBuilder {

  def addClassDescription(classDescription: ClassDescription): Unit;

  def addThreadOrLoopDescription(threadOrLoopDescription: ThreadOrLoopDescription): Unit;

}
