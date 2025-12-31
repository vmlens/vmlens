package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexMonitorOrAtomicWithLock}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.vmlens.report.dominatortree.UIStateElementSortKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

class SummaryEvent(val threadIndex: Int, 
                   val dominatorTreeCounter  : Int,
                   val memoryAccessKey : MemoryAccessKey,
                   val sortKey : UIStateElementSortKey) extends CreateDominatorTreeEvent  {

  val operationSet = new mutable.HashSet[Int];

  override def add(stack: CreateGraphStack, 
                   alreadyAdded: mutable.HashSet[DominatorTreeVertex], 
                   graph: Graph[DominatorTreeVertex, DefaultEdge]): Unit = {
    stack.addToGraph(graph, alreadyAdded);
  
    stack.addLeaf(memoryAccessKey , operationSet.toSet ,sortKey ,graph);
  }
}

object SummaryEvent {
  
  def apply[MEMORY_ACCESS_KEY <: MemoryAccessKey](event :  EventForSummary[MEMORY_ACCESS_KEY],
                                                  sortKey : UIStateElementSortKey): SummaryEvent = {
    val summary = new SummaryEvent(
      event.threadIndex,
      event.dominatorTreeCounter,
      event.memoryAccessKey,
      sortKey)
    summary.operationSet.add(event.operation)
    summary
  }
  
}
