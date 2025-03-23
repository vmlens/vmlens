package com.anarsoft.race.detection.groupnonvolatile

import com.anarsoft.race.detection.createlastthreadposition.LastThreadPositionMap
import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupNonVolatileElement {

  def addLastThreadPosition(lastThreadPositionMap: LastThreadPositionMap): Unit;

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def sort(partialOrder: PartialOrder): GroupNonVolatileMemoryAccessElementForResult;


  
}