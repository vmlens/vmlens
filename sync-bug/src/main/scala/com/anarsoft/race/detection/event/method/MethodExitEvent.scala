package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeMethodExit
import com.anarsoft.race.detection.createstacktrace.{MethodEvent, StacktraceNodeStack}
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodExitEvent extends LoadedMethodEvent with CreateDominatorTreeMethodExit {

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodExit();

}
