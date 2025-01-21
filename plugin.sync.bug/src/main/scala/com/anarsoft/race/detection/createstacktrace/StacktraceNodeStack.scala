package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode

import scala.collection.mutable.Stack

class StacktraceNodeStack(val threadIndex: Int) {

  private val stack = Stack[StacktraceNode]();

  def methodEnter(methodId: Int): StacktraceNode = {
    val newElement =
      if (stack.isEmpty) {
        new StacktraceNodeRoot(methodId);
      } else {
        new StacktraceNodeIntermediate(methodId, stack.top);
      }
    stack.push(newElement)
    newElement;
  }

  def methodExit(): StacktraceNode = {
    val current = stack.pop();
    if (stack.isEmpty) {
      current;
    } else {
      stack.top
    }
  }

}
