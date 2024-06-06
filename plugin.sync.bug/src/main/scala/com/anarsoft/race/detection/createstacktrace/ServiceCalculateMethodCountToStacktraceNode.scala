package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable.{HashMap, ListBuffer, Map, Stack}

class ServiceCalculateMethodCountToStacktraceNode {


  def process(methodEventArray: EventArray[MethodEvent]): Map[Long, Array[StacktraceNode]] = {
    val result = HashMap[Long, Array[StacktraceNode]]()
    var currentList = ListBuffer[StacktraceNode]()
    var currentStack: Option[StacktraceNodeStack] = None;

    for (event <- methodEventArray) {
      currentStack = currentStack match {
        case None => {
          Some(new StacktraceNodeStack(event.threadId))
        }
        case Some(x) => {
          if (x.threadId == event.threadId) {
            Some(x);
          } else {
            result.put(x.threadId, currentList.toArray)
            currentList = ListBuffer[StacktraceNode]()
            Some(new StacktraceNodeStack(event.threadId))
          }
        }
      }
      currentList += event.createStacktraceNode(currentStack.get);
    }

    currentStack match {
      case None => {}
      case Some(x) => {
        result.put(x.threadId, currentList.toArray)
      }
    }
    result;

  }

}
