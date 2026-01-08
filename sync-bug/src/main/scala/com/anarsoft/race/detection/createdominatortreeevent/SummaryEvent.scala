package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexMonitorOrAtomicWithLock}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.anarsoft.race.detection.dominatortree.VertexAtomicNonBlockingOrVolatile

import com.vmlens.report.dominatortree.UIStateElementSortKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import  com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.GenericDominatorMemoryAccessKey

import scala.collection.mutable

class SummaryEvent[MEMORY_ACCESS_KEY] (val threadIndex: Int,
                                       val dominatorTreeCounter  : Int,
                                       val memoryAccessKey : GenericDominatorMemoryAccessKey[MEMORY_ACCESS_KEY],
                                       val sortKey : UIStateElementSortKey) extends CreateDominatorTreeEvent  {

  val operationSet = new mutable.HashSet[Int];

  override def add(context : CreateDominatorTreeContext): Unit = {
    context.stack.addAllElementsOfStackToGraph(context.graph, context.alreadyAdded);

    context.stack.addLeaf(memoryAccessKey , operationSet.toSet ,sortKey ,context.memoryKeyToVertex , context.graph);
  }
}

object SummaryEvent {
  
  def apply[MEMORY_ACCESS_KEY <: GenericDominatorMemoryAccessKey[MEMORY_ACCESS_KEY]](event :  EventForSummary[MEMORY_ACCESS_KEY],
                                                                                     sortKey : UIStateElementSortKey): SummaryEvent[MEMORY_ACCESS_KEY] = {
    val summary = new SummaryEvent[MEMORY_ACCESS_KEY](
      event.threadIndex,
      event.dominatorTreeCounter,
      event.memoryAccessKey,
      sortKey)
    summary.operationSet.add(event.operation)
    summary
  }
  
}
