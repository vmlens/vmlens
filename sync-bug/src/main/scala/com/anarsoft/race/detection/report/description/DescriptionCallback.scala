package com.anarsoft.race.detection.report.description

import com.vmlens.trace.agent.bootstrap.description.{ClassDescription, ThreadLoopOrAutomaticTestDescription}

trait DescriptionCallback {

  def addClassDescription(classDescription: ClassDescription): Unit;

  def addThreadOrLoopDescription(threadOrLoopDescription: ThreadLoopOrAutomaticTestDescription): Unit;
 
}
