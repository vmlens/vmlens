package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.Stack



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
    // can at least currently happen because of exceptions
    if(stack.nonEmpty) {
      stack.pop();
    }
    if (stack.isEmpty) {
      null;
    } else {
      stack.top
    }
  }

}
