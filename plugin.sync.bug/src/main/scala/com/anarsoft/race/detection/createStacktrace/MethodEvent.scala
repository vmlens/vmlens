package com.anarsoft.race.detection.createStacktrace

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodEvent extends ThreadIdAndMethodCounter with EventWithLoopAndRunId {


  def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode;


}
