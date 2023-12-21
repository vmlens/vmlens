package com.anarsoft.race.detection.stacktrace

import com.anarsoft.race.detection.event.load.EventWithLoopAndRunId

trait MethodEvent extends ThreadIdAndMethodCounter with EventWithLoopAndRunId {


  def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode;


}
