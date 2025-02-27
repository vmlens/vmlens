package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class ServiceCalculateMethodCountToStacktraceNode {


  def process(methodEventArray: EventArray[MethodEvent]): mutable.Map[Int, Array[StacktraceNode]] = {
    methodEventArray.sort(new MethodEventOrdering());

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
            result.put(x.threadIndex, createArray(currentList))
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
        result.put(x.threadIndex, createArray(currentList))
      }
    }
    result;

  }

  /**
   * methodCount starts with 1 for the first method call
   * there are events with methodCount 0 so we need to create one more element
   *
   */
  def createArray(list: ListBuffer[StacktraceNode]): Array[StacktraceNode] = {
    val orig = list.toArray;
    val withAdditionalElement = Array.ofDim[StacktraceNode](orig.length + 1)
    Array.copy(orig, 0, withAdditionalElement, 1, orig.length)

    withAdditionalElement

  }


}
