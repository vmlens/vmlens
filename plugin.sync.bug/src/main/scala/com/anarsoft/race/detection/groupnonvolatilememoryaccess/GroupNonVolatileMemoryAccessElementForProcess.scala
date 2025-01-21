package com.anarsoft.race.detection.groupnonvolatilememoryaccess

import com.anarsoft.race.detection.sortnonvolatilememoryaccess.PartialOrder
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait GroupNonVolatileMemoryAccessElementForProcess {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit;

  def sort(partialOrder: PartialOrder): GroupNonVolatileMemoryAccessElementForResult;


  
}