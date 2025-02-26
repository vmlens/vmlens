package com.anarsoft.race.detection.groupnonvolatile

import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupNonVolatileElement {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def sort(partialOrder: PartialOrder): GroupNonVolatileMemoryAccessElementForResult;


  
}