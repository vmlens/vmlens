package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, InternalNode, LeafNode, VertexAtomicNonBlockingOrVolatile, VertexMethod, VertexRoot}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIStateElementSortKey
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
  
  def addToGraph(graph : Graph[DominatorTreeVertex,DefaultEdge], 
                 alreadyAdded : mutable.HashSet[DominatorTreeVertex]): Unit = {
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
  
  def addLeaf(memoryAccessKey: MemoryAccessKey,
               operationSet : Set[Int],
              sortKey : UIStateElementSortKey,
              graph : Graph[DominatorTreeVertex,DefaultEdge]) : Unit = {
    if (stack.isEmpty) {
      val leaf = new VertexAtomicNonBlockingOrVolatile(memoryAccessKey, operationSet,sortKey);
      graph.addVertex(leaf)
      graph.addEdge(root, leaf);
    } else {
      val leaf = new VertexAtomicNonBlockingOrVolatile(memoryAccessKey, operationSet,  sortKey);
      graph.addVertex(leaf)
      graph.addEdge(stack.top , leaf);
    }
  }
  

}
