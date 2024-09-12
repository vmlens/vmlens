package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode

class EventWithStacktraceNodeGuineaPig(val threadIndex: Int,
                                       val methodCounter: Int) extends EventWithStacktraceNode {

  var node: StacktraceNode = null;

  override def setStacktraceNode(node: StacktraceNode): Unit = {
    this.node = node;
  }
}
