package com.anarsoft.race.detection.stacktrace

trait StacktraceNode {

  def methodId: Int;

  def getParent: Option[StacktraceNode]

}
