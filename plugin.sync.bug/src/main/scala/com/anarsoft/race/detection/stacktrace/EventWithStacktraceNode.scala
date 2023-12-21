package com.anarsoft.race.detection.stacktrace

trait EventWithStacktraceNode extends ThreadIdAndMethodCounter {

  def setStacktraceNode(node: StacktraceNode): Unit

}
