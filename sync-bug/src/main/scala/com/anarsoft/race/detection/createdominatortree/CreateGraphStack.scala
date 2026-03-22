package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, NormalizeVertex, DefaultVertex, VertexState, VertexLockOrMonitor, VertexMethod, VertexRoot}
import com.anarsoft.race.detection.dominatortree.key._
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIStateElementSortKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey

import scala.collection.mutable

class CreateGraphStack(val normalizeVertex : NormalizeVertex,
                       val threadIndex : Int) {

  private val stack = Stack[DefaultVertexKey]();

  def methodEnter(methodId: Int) : Unit = {
    val vertex = VertexKeyMethod(methodId);
    stack.push(vertex)
  }

  def methodExit(): Unit = {
    // can at least currently happen because of exceptions
    if (stack.nonEmpty) {
      stack.pop();
    }
  }
  
  def monitorEnter(monitorId : Int): DominatorTreeVertex = {
    val key = VertexKeyMonitor(monitorId);
    stack.push(key)
    addAllElementsOfStackToGraph();
    normalizeVertex.addDefault(key);
  }

  def monitorExit(): Unit = {
    if (stack.nonEmpty) {
      stack.pop();
    }
  }
  
  def lockEnter(lockType : ReportLockType, id : Int) : DominatorTreeVertex = {
    val key = VertexKeyLock(lockType, id)
    stack.push(key)
    addAllElementsOfStackToGraph();
    normalizeVertex.addDefault(key);
  }

  def lockExit(lockType : ReportLockType, 
               id : Int): Unit = {
    stack.removeFirst(VertexKeyLock(lockType,id))
  }
  
  def addLeaf(memoryAccessKey: DominatorMemoryAccessKey,
               operationSet : Set[Int],
              sortKey : UIStateElementSortKey) : Unit = {
    addAllElementsOfStackToGraph();
    
    val leaf = normalizeVertex.addState(StateVertexKey(memoryAccessKey,sortKey));

    leaf.operationSet.addAll(operationSet)

    if (stack.isEmpty) {
      normalizeVertex.graph.addEdge(normalizeVertex.root, leaf);
    } else {
      normalizeVertex.graph.addEdge(normalizeVertex.addDefault(stack.top) , leaf);
    }
  }
  
  // Visible for test
  // Used in test builder
  def addAllElementsOfStackToGraph(): Unit = {
    var previous : DefaultVertex = normalizeVertex.root;
    for(elem <- stack){
      val vertex = normalizeVertex.addDefault(elem)
      normalizeVertex.graph.addEdge(previous,vertex);
      previous = vertex;
    }
  }

}
