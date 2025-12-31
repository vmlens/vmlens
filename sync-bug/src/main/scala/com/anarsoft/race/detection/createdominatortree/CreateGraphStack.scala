package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, InternalNode, LeafNode, VertexAtomicNonBlockingOrVolatile, VertexMethod, VertexRoot}
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIStateElementSortKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import  com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey

import scala.collection.mutable



class CreateGraphStack(val root :  VertexRoot, val threadIndex : Int) {

  private val stack = Stack[InternalNode]();

  def methodEnter(methodId: Int): Unit = {
    stack.push( VertexMethod(methodId))
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
  
  def addLeaf(memoryAccessKey: DominatorMemoryAccessKey,
               operationSet : Set[Int],
              sortKey : UIStateElementSortKey,
              memoryKeyToVertex : mutable.HashMap[DominatorMemoryAccessKey,VertexAtomicNonBlockingOrVolatile],
              graph : Graph[DominatorTreeVertex,DefaultEdge]) : Unit = {

    val leaf = 
    memoryKeyToVertex.get(memoryAccessKey) match {
      case Some(x) => {
        x.operationSet.addAll(operationSet)
        x;
      }
      case None => {
        val newLeaf =  new VertexAtomicNonBlockingOrVolatile(memoryAccessKey, sortKey);
        newLeaf.operationSet.addAll(operationSet)
        newLeaf;
      }
    }
    
    if (stack.isEmpty) {
      graph.addVertex(leaf)
      graph.addEdge(root, leaf);
    } else {
      graph.addVertex(leaf)
      graph.addEdge(stack.top , leaf);
    }
  }
  

}
