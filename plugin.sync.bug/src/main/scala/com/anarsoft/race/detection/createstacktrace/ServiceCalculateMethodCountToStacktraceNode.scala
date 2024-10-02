package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Stack}

class ServiceCalculateMethodCountToStacktraceNode {


  def process(methodEventArray: EventArray[MethodEvent]): mutable.Map[Int, Array[StacktraceNode]] = {
    val result = mutable.HashMap[Int, Array[StacktraceNode]]()
    var currentList = ListBuffer[StacktraceNode]()
    var currentStack: Option[StacktraceNodeStack] = None;

    for (event <- methodEventArray) {
      currentStack = currentStack match {
        case None => {
          Some(new StacktraceNodeStack(event.threadIndex))
        }
        case Some(x) => {
          if (x.threadIndex == event.threadIndex) {
            Some(x);
          } else {
            result.put(x.threadIndex, currentList.toArray)
            currentList = ListBuffer[StacktraceNode]()
            Some(new StacktraceNodeStack(event.threadIndex))
          }
        }
      }
      currentList += event.createStacktraceNode(currentStack.get);
    }

    currentStack match {
      case None => {}
      case Some(x) => {
        result.put(x.threadIndex, currentList.toArray)
      }
    }
    result;

  }

}
