package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createdominatortreeevent.BuildDominatorTreeContext
import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupInterleaveElement extends GroupInterleaveElementForResult {
  
  def addLastThreadPosition(lastThreadPositionMap : LastThreadPositionMap) : Unit;
  
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit;
  
  def addToBuildDominatorTreeContext(buildDominatorTreeContext : BuildDominatorTreeContext) : Unit;
}
