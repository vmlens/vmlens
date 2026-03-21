package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable

object CompareGraph {

  def shouldBe( actual : Graph[DominatorTreeVertex, DefaultEdge],
                expected : Graph[DominatorTreeVertex, DefaultEdge]): Unit = {

    if( ! actual.vertexSet().equals(expected.vertexSet())) {

      throw new RuntimeException("vertex not the same");
    } else {
      if(actual.edgeSet().size() != expected.edgeSet().size()) {
        throw new RuntimeException("edge set size not the same");
      }
      
      val actualVertices = new mutable.HashSet[EdgeForTest]
      val actualIter =  actual.edgeSet().iterator()
      while(actualIter.hasNext) {
        val vertex = actualIter.next()
        actualVertices.add(EdgeForTest(actual.getEdgeSource(vertex),actual.getEdgeTarget(vertex)))
      }

      val expectedIter = expected.edgeSet().iterator()
      while (expectedIter.hasNext) {
        val vertex = expectedIter.next()
        val expectedVertex = EdgeForTest(actual.getEdgeSource(vertex), actual.getEdgeTarget(vertex))
        if( ! actualVertices.contains(expectedVertex)) {
          System.err.println(expectedVertex);
          throw new RuntimeException("edge not found");
        }
        
      }
      
    }

  }
}
