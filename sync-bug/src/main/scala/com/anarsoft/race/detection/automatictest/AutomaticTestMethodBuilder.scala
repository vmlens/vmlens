package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.util.Stack
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

/**
 * this class is per thread index
 * 
 */
class AutomaticTestMethodBuilder(private val automaticTestMethodId : Int,
                                 private val automaticTestType : Int ,
                                 private val automaticTest : AutomaticTest) {

  private val stack = new Stack[AutomaticTestMethodVertex]();
  private val alreadyAdded = new mutable.HashSet[AutomaticTestMethodVertex]
  private val graph = new DefaultDirectedGraph[AutomaticTestMethodVertex, DefaultEdge](classOf[DefaultEdge]);
  private val rootSet = new mutable.HashSet[AutomaticTestMethodVertex]() 

  def build() : Unit = {
    automaticTest.put(automaticTestMethodId, new AutomaticTestMethod(automaticTestType,graph,rootSet.toSet))
  }


  def methodEnter(methodId : Int) : Unit = {
    val vertex = AutomaticTestMethodVertex(methodId);
    if(! alreadyAdded.contains(vertex)) {
      graph.addVertex(vertex)
      alreadyAdded.add(vertex)
    }
    if(! stack.isEmpty) {
      graph.addEdge(stack.top,vertex )
    } else {
      rootSet.add(vertex)
    }
    stack.push(vertex)
  }

  def methodExit(): Unit = {
    if(! stack.isEmpty) {
      stack.pop()
    }
  }
  

}
