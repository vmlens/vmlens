package com.anarsoft.race.detection.event.method

import com.anarsoft.race.detection.createStacktrace.{MethodEvent, StacktraceNodeStack}
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodEnterEvent extends LoadedMethodEvent {

  def methodId: Int;

  override def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode = stack.methodEnter(methodId);

}
