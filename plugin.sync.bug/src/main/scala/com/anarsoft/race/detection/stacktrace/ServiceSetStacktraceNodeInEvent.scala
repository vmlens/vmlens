package com.anarsoft.race.detection.stacktrace

import com.anarsoft.race.detection.util.EventArray

class ServiceSetStacktraceNodeInEvent {

  def process(eventArray: EventArray[EventWithStacktraceNode], threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    var current: Option[ThreadIdToArray] = None;

    for (event <- eventArray) {
      current = current match {
        case None => threadIdToStacktraceNodeArray.get(event.threadId)
          .map(array => ThreadIdToArray(event.threadId, array))

        case Some(x) => {
          if (x.threadId == event.threadId) {
            Some(x)
          } else {
            threadIdToStacktraceNodeArray.get(event.threadId)
              .map(array => ThreadIdToArray(event.threadId, array))
          }
        }
      }

      event.setStacktraceNode(current.get.array(event.methodCounter));

    }
  }

  private case class ThreadIdToArray(threadId: Long, array: Array[StacktraceNode]);

}
