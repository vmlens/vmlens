package com.anarsoft.race.detection.event.load

import com.anarsoft.race.detection.createstacktrace.ThreadIdAndMethodCounter
import com.anarsoft.race.detection.event.load.LoopAndRunIdAndEventArray
import com.anarsoft.race.detection.util.EventArray

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
