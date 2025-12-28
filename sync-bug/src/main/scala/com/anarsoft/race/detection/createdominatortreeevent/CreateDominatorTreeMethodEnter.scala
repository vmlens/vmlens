package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

trait CreateDominatorTreeMethodEnter extends CreateDominatorTreeEvent {

  def methodId: Int;
  
  override def add(stack: CreateGraphStack, 
                   alreadyAdded: mutable.HashSet[DominatorTreeVertex], 
                   graph: Graph[DominatorTreeVertex, DefaultEdge]): Unit = {
    stack.methodEnter(methodId)
  }
}
