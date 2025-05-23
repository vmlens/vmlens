package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.assertion.OnEvent

trait GroupInterleaveElement extends GroupInterleaveElementForResult {
  
  def addLastThreadPosition(lastThreadPositionMap : LastThreadPositionMap) : Unit;
  
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit;
  
  def addToOnEvent(onEvent : OnEvent) : Unit;
  
}
