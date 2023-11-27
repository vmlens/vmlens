package com.anarsoft.race.detection.stacktrace

import com.anarsoft.race.detection.util.ThreadIdAndMethodCounter

trait MethodEvent extends ThreadIdAndMethodCounter {


  def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode;


}
