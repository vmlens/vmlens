package com.anarsoft.race.detection.stacktrace

import com.anarsoft.race.detection.util.ThreadIdAndMethodCounter

trait EventWithStacktraceNode extends ThreadIdAndMethodCounter {

  def setStacktraceNode(node: StacktraceNode): Unit

}
