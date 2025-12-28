package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, InternalNode, VertexMethod, VertexRoot}
import com.anarsoft.race.detection.util.Stack
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable



class CreateGraphStack(val root :  VertexRoot, val threadIndex : Int) {

  private val stack = Stack[InternalNode]();

  def methodEnter(methodId: Int): Unit = {
    val newElement =
      if (stack.isEmpty) {
        VertexMethod(methodId,root);
      } else {
        VertexMethod(methodId, stack.top);
      }
    stack.push(newElement)
  }

  def methodExit(): Unit = {
    // can at least currently happen because of exceptions
    if (stack.nonEmpty) {
      stack.pop();
    }
  }
  
  def addToGraph(graph : Graph[DominatorTreeVertex,DefaultEdge], alreadyAdded : mutable.HashSet[DominatorTreeVertex]): Unit = {
    var previous : InternalNode = root;
    for(elem <- stack){
      if(! alreadyAdded.contains(elem)) {
        graph.addVertex(elem);
        alreadyAdded.add(elem)
      }
      graph.addEdge(previous,elem);
      previous = elem;
    } 
  }
  
  

}
