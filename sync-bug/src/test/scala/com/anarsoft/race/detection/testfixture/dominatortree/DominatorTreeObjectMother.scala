package com.anarsoft.race.detection.testfixture.dominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexLock, VertexMethod, VertexRoot}
import com.anarsoft.race.detection.report.element.runelementtype.ReportLockType.REENTRANT_LOCK
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

object DominatorTreeObjectMother {
  
  val ROOT =  new VertexRoot()
  
  def oneLockCallGraph() : DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge]= {
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);

    graph.addVertex(ROOT)
    graph.addVertex(VertexMethod(5))
    graph.addVertex(VertexMethod(7))
    graph.addVertex(VertexLock(REENTRANT_LOCK, 1))

    graph.addEdge(ROOT, VertexMethod(5))
    graph.addEdge(VertexMethod(5) ,VertexMethod(7) )
    graph.addEdge(VertexMethod(7) ,VertexMethod(5) )
    graph.addEdge(VertexMethod(5), VertexLock(REENTRANT_LOCK, 1))
    
    graph;
  }
  
  def dominatorTreeOneLock() : DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge]= {
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);

    graph.addVertex(ROOT)
    graph.addVertex(VertexMethod(5))
    graph.addVertex(VertexMethod(7))
    graph.addVertex(VertexLock(REENTRANT_LOCK, 1))

    graph.addEdge(ROOT, VertexMethod(5))
    graph.addEdge(VertexMethod(5), VertexMethod(7))
    graph.addEdge(VertexMethod(5), VertexLock(REENTRANT_LOCK, 1))

    graph;
  }

  def filteredDominatorTreeOneLock(): DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge] = {
    val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);

    graph.addVertex(ROOT)
    graph.addVertex(VertexMethod(5))
    graph.addVertex(VertexLock(REENTRANT_LOCK, 1))

    graph.addEdge(ROOT, VertexMethod(5))
    graph.addEdge(VertexMethod(5), VertexLock(REENTRANT_LOCK, 1))

    graph;
  }
  

}
