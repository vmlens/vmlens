package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.collection.mutable

class TreeBuilderTest  extends AnyFlatSpec with Matchers {

  "method calling method " should "lead to one branch" in {
    val ROOT = new VertexRoot()
    val createGraphStack = null//new CreateGraphStack(ROOT,0);
    val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]()
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);
    graph.addVertex(ROOT)
    
  }

}
