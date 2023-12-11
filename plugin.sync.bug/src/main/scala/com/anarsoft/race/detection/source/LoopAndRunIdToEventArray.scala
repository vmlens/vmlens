package com.anarsoft.race.detection.source

import com.anarsoft.race.detection.util.{EventArray, ThreadIdAndMethodCounter}

import scala.collection.mutable.Stack

class LoopAndRunIdToEventArray[EVENT <: ThreadIdAndMethodCounter] {

  private val loopAndRunIdToEventArray = Stack[LoopAndRunIdAndEventArray[EVENT]]();

  def push(elem: LoopAndRunIdAndEventArray[EVENT]): Unit = {
    loopAndRunIdToEventArray.push(elem);
  }

  def pop(): LoopAndRunIdAndEventArray[EVENT] = {
    loopAndRunIdToEventArray.pop();
  }

}
