package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.anarsoft.race.detection.dominatortree.VertexAtomicNonBlockingOrVolatile

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

trait CreateDominatorTreeMethodEnter extends CreateDominatorTreeEvent {

  def methodId: Int;
  
  override def add(context : CreateDominatorTreeContext): Unit = {
    context.stack.methodEnter(methodId)
  }
}
