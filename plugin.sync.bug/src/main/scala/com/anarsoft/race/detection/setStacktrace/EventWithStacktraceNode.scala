package com.anarsoft.race.detection.setStacktrace

import com.anarsoft.race.detection.createStacktrace.ThreadIdAndMethodCounter
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait EventWithStacktraceNode extends ThreadIdAndMethodCounter {

  def setStacktraceNode(node: StacktraceNode): Unit

}
