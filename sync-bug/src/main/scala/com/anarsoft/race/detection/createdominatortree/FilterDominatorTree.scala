package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.util.Stack
import org.jgrapht.{Graph, Graphs}
import org.jgrapht.graph.{DefaultEdge, SimpleDirectedGraph}
import org.jgrapht.traverse.DepthFirstIterator

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class FilterDominatorTree(root : VertexRoot,tree : Graph[DominatorTreeVertex, DefaultEdge]) {

  val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]
  val filtered = new SimpleDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge])
  alreadyAdded.add(root);
  filtered.addVertex(root)

  def filter() :
        SimpleDirectedGraph[DominatorTreeVertex, DefaultEdge] = {

    val iter = new DepthFirstIterator[DominatorTreeVertex, DefaultEdge](tree, root)
     while(iter.hasNext) {
       val current = iter.next();
       if(!current.isMethodCall) {
         addToFiltered(current);
       }
     }
    filtered;
  }

  def addToFiltered(node : DominatorTreeVertex): Unit = {
    val path = new ArrayBuffer[DominatorTreeVertex]
    var current = node;
    while(current != root) {
      path.append(current)
      current =  Graphs.predecessorListOf(tree,current).get(0);
    }
    path.append(current)
    
    var parent : Option[DominatorTreeVertex] = None;
    for(element <- path.reverse) {
      filtered.addVertex(element)
      parent.foreach( (p) => { filtered.addEdge(p,element)  }  )
      parent = Some(element)
    }
  }
  
}
