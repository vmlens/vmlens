package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.createstacktrace.WithMethodCounter
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait WithSetStacktraceNode extends WithMethodCounter {

  var stacktraceNode: Option[StacktraceNode] = None;

  final def setStacktraceNode(node: StacktraceNode): Unit = {
    this.stacktraceNode = Some(node);
  }
}
