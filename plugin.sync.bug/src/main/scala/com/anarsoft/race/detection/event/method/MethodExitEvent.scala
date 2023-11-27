package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.stacktrace.{MethodEvent, StacktraceNode, StacktraceNodeStack}

trait MethodExitEvent extends LoadedMethodEvent {

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodExit();

}
