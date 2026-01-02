package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.anarsoft.race.detection.dominatortree.VertexAtomicNonBlockingOrVolatile

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

trait CreateDominatorTreeMethodExit  extends CreateDominatorTreeEvent {

  override def add(stack: CreateGraphStack,
                   alreadyAdded: mutable.HashSet[DominatorTreeVertex],
                   memoryKeyToVertex : mutable.HashMap[DominatorMemoryAccessKey,VertexAtomicNonBlockingOrVolatile],
                   graph: Graph[DominatorTreeVertex, DefaultEdge]): Unit = {
    stack.methodExit()
  }
  
}
