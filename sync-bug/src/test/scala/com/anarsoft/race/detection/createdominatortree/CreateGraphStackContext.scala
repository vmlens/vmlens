package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable

class CreateGraphStackContext(val root :  VertexRoot,
                              val graph : Graph[DominatorTreeVertex,DefaultEdge],
                              val alreadyAdded : mutable.HashSet[DominatorTreeVertex]) {
  
  def createGraphStack(threadIndex : Int) : CreateGraphStack  = new CreateGraphStack(root,threadIndex,graph,alreadyAdded);

}

object CreateGraphStackContext {
  
  def apply() : CreateGraphStackContext  = {
    val root = new VertexRoot();
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge])
    val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]
    alreadyAdded.add(root)
    graph.addVertex(root)
    new CreateGraphStackContext(root,graph,alreadyAdded);
  }
  
  
}
