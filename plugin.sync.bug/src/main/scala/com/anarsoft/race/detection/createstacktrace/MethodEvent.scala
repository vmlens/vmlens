package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodEvent extends ThreadIndexAndMethodCounter with EventWithLoopAndRunId {


  def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode;


}
