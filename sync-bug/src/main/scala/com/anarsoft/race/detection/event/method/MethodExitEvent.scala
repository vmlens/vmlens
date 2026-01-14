package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.createautomatictest.CreateAutomaticTestMethodExitEvent
import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeMethodExit
import com.anarsoft.race.detection.createstacktrace.{MethodEvent, StacktraceNodeStack}
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodExitEvent extends LoadedMethodEvent 
  with CreateDominatorTreeMethodExit
  with CreateAutomaticTestMethodExitEvent  {

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodExit();

}
