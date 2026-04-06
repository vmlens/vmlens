package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.NormalizeVertex
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable

class CreateGraphStackContext(val normalizeVertex : NormalizeVertex) {
  
  def createGraphStack(threadIndex : Int) : CreateGraphStack  = new CreateGraphStack(normalizeVertex,threadIndex);

}

object CreateGraphStackContext {
  
  def apply() : CreateGraphStackContext  = {
    val normalizeVertex = new NormalizeVertex();
    new CreateGraphStackContext(normalizeVertex);
  }
  
  
}
