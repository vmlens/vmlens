package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SetStacktraceNodeInEvent {

  def process(eventArray: EventArray[WithSetStacktraceNode], threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    for (event <- eventArray) {
      event.setStacktraceNode(threadIdToStacktraceNodeArray.get(event.threadIndex).get.array(event.methodCounter));
    }
  }

}
