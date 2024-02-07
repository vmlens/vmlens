package com.anarsoft.race.detection.setStacktrace

import com.anarsoft.race.detection.setStacktrace.EventWithStacktraceNode
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SetStacktraceNodeInEvent {

  def process(eventArray: EventArray[EventWithStacktraceNode], threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    for (event <- eventArray) {
      event.setStacktraceNode(threadIdToStacktraceNodeArray.get(event.threadId).get.array(event.methodCounter));
    }
  }

}
