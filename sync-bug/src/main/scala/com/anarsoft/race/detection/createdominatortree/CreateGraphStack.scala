package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, InternalNode, LeafNode, VertexAtomicNonBlockingOrVolatile, VertexLock, VertexMethod, VertexMonitor, VertexRoot}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType
import com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey.MemoryAccessKey
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIStateElementSortKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey

import scala.collection.mutable

class CreateGraphStack(val root :  VertexRoot, 
                       val threadIndex : Int,
                       val graph : Graph[DominatorTreeVertex,DefaultEdge],
                       val alreadyAdded : mutable.HashSet[DominatorTreeVertex]) {

  private val stack = Stack[InternalNode]();

  def methodEnter(methodId: Int): DominatorTreeVertex = {
    val vertex = VertexMethod(methodId,stack.size);
    stack.push(vertex)
    vertex;
  }

  def methodExit(): Unit = {
    // can at least currently happen because of exceptions
    if (stack.nonEmpty) {
      stack.pop();
    }
  }
  
  def monitorEnter(monitorId : Int): DominatorTreeVertex = {
    val vertex = VertexMonitor(monitorId);
    stack.push(vertex)
    addAllElementsOfStackToGraph();
    vertex
  }

  def monitorExit(): Unit = {
    if (stack.nonEmpty) {
      stack.pop();
    }
  }
  
  def lockEnter(lockType : ReportLockType, id : Int) : DominatorTreeVertex = {
    val vertex = VertexLock(lockType, id)
    stack.push(vertex)
    addAllElementsOfStackToGraph();
    vertex
  }

  def lockExit(lockType : ReportLockType, 
               id : Int): Unit = {
    stack.removeFirst(VertexLock(lockType,id))
  }
  
  def addLeaf(memoryAccessKey: DominatorMemoryAccessKey,
               operationSet : Set[Int],
              sortKey : UIStateElementSortKey,
              memoryKeyToVertex : mutable.HashMap[DominatorMemoryAccessKey,VertexAtomicNonBlockingOrVolatile]) : Unit = {
    addAllElementsOfStackToGraph();
    
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
  
  // Visible for test
  // Used in test builder
  def addAllElementsOfStackToGraph(): Unit = {
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
