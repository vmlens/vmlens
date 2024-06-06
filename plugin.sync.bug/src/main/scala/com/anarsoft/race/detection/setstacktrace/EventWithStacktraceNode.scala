package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.createstacktrace.ThreadIdAndMethodCounter
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait EventWithStacktraceNode extends ThreadIdAndMethodCounter {

  def setStacktraceNode(node: StacktraceNode): Unit

}
