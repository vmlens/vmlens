package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.partialorder.PartialOrderBuilder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupInterleaveElement extends GroupInterleaveElementForResult {
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit;


}
