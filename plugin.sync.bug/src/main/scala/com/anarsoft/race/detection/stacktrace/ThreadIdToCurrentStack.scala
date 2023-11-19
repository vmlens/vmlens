package com.anarsoft.race.detection.stacktrace

import scala.collection.mutable.{Map, Stack}

class ThreadIdToCurrentStack {

  private var threadIdToStack = Map[Long, Stack[StacktraceNode]]()

  def onMethodEnter(threadId: Long, methodId: Int): Unit = {

  }

  def onMethodExit(threadId: Long, methodId: Int): Unit = {

  }

  def clear(): Unit = {
    threadIdToStack = Map[Long, Stack[StacktraceNode]]()
  }


}
