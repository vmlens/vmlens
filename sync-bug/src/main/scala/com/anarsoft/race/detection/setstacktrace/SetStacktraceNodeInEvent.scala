package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class SetStacktraceNodeInEvent {

  def process(eventArray: EventArray[WithSetStacktraceNode], threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    for (event <- eventArray) {
      threadIdToStacktraceNodeArray.get(event.threadIndex) match {
        case Some(x) => {
          val stacktraceNode = x.array(event.methodCounter);
          if (stacktraceNode != null) {
            event.setStacktraceNode(stacktraceNode);
          }

        }
        case None => {
          // happens for example for the main thread
        }
      }
    }
  }

}
