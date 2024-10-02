package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.createstacktrace.ThreadIndexAndMethodCounter
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait EventWithStacktraceNode extends ThreadIndexAndMethodCounter {

  var stacktraceNode: Option[StacktraceNode] = None;

  final def setStacktraceNode(node: StacktraceNode): Unit = {
    this.stacktraceNode = Some(node);
  }

}
