package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.stacktrace.{MethodEvent, StacktraceNode, StacktraceNodeStack}

trait MethodEnterEvent extends LoadedMethodEvent {

  def methodId: Int;

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodEnter(methodId);

}
