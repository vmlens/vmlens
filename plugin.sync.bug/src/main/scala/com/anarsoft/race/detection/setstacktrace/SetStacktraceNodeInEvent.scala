package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SetStacktraceNodeInEvent {

  def process(eventArray: EventArray[WithSetStacktraceNode], threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    for (event <- eventArray) {
      threadIdToStacktraceNodeArray.get(event.threadIndex) match {
        case Some(x) => {
          event.setStacktraceNode(x.array(event.methodCounter));
        }
        case None => {
          // happens for example for the main thread
        }
      }
    }
  }

}
