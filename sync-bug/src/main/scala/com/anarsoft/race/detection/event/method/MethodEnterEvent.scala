package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeMethodEnter
import com.anarsoft.race.detection.createstacktrace.{MethodEvent, StacktraceNodeStack}
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodEnterEvent extends LoadedMethodEvent with CreateDominatorTreeMethodEnter {

  def methodId: Int;

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodEnter(methodId);

}
