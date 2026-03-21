package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTree, DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.rundata.RunData
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import java.util
import scala.collection.mutable

/**
 * creates the dominator tree
 *     sorted per thread per position
 *     stack based,
 *     if leaf add to graph, also add all parents if not already added
 *     for volatile fields we create a new summary object containing a set of all memory operations
 *     and filter them for only multithreaded accessed fields, see ObjectHashCodeMap
 *
 */
class CreateDominatorTreeFromRunData {

  val loopIdToAlreadyProcessed = new mutable.HashMap[Int,Boolean]

  def process(runData : RunData) : Option[DominatorTree] = {
    if( loopIdToAlreadyProcessed.contains(runData.loopAndRunId.loopId) ) {
      None
    } else {
      val root = new VertexRoot()
      val graph = new CreateGraphFromEvents(root).process(runData);
      val dominatorTree = CreateDominatorTreeFromRunData.dominatorTree(root,graph)

      println(dominatorTree)
      println(graph)

      Some(new  DominatorTree(dominatorTree,root,graph));
    }
  }

}

object CreateDominatorTreeFromRunData {
  
  def dominatorTree(root :  VertexRoot,graph : Graph[DominatorTreeVertex, DefaultEdge]) :  
     Graph[DominatorTreeVertex, DefaultEdge] = {
    val dominatorTreeUnfiltered = Dominators[DominatorTreeVertex, DefaultEdge](graph, root).getDominatorTree
    println(dominatorTreeUnfiltered)
    new FilterDominatorTree(root, dominatorTreeUnfiltered).filter();
  }
  
}
