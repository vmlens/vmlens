package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.util.Stack
import org.jgrapht.{Graph, Graphs}
import org.jgrapht.graph.{DefaultEdge, SimpleDirectedGraph}

import scala.collection.mutable

class FilterDominatorTree {

  def filter(tree : Graph[DominatorTreeVertex, DefaultEdge],
             root : VertexRoot) :
        SimpleDirectedGraph[DominatorTreeVertex, DefaultEdge] = {

    val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]


    val filtered =
      new SimpleDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge])
      alreadyAdded.add(root);
      filtered.addVertex(root)

    val stack = new Stack[DominatorTreeVertex]
    stack.push(root)

    while (!stack.isEmpty) {
      val vertex = stack.pop()

      val children = Graphs.successorListOf(tree, vertex)
      if(children.isEmpty) {
        if(vertex.isDominatorTreeLeaf) {
          var current: Option[DominatorTreeVertex] = Some(vertex);
          if (!alreadyAdded.contains(vertex)) {
            alreadyAdded.add(vertex)
            filtered.addVertex(vertex)
          }

          while (!current.isEmpty) {
            val set = tree.incomingEdgesOf(current.get)
            if (set.size() > 1) {
              throw new RuntimeException("not a tree");
            }
            if (!set.isEmpty) {
              val parent = tree.getEdgeSource(set.iterator().next());
              if (!alreadyAdded.contains(parent)) {
                alreadyAdded.add(parent)
                filtered.addVertex(parent)
              }

              filtered.addEdge(parent, current.get)
              current = Some(parent)

            } else {
              current = None;
            }
          }
        }
      } else {
        val it = children.iterator()
        while (it.hasNext) {
          val child = it.next()
          stack.push(child)
        }
      }
    }
    filtered;
  }


}
