package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.util.Stack
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.jgrapht.{Graph, Graphs}

import scala.collection.mutable

class CreateDominatorTree(graph: Graph[DominatorTreeVertex, DefaultEdge],
                          root: VertexRoot) {

  val forest = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge])

  def buildDominatorTree(): Unit = {
    // The first is empty
    // The vertex whose number is i
    val vertex = Array.ofDim[DominatorTreeVertex](graph.vertexSet().size() + 1)

    // The parents in the spanning tree
    val childToParent = new mutable.HashMap[DominatorTreeVertex, DominatorTreeVertex]()

    var n = 0;
    val stack = new Stack[DominatorTreeVertex]
    stack.push(root)

    while (!stack.isEmpty) {
      n = n + 1;

      val current = stack.pop()
      current.semi = n;
      vertex(n) = current;

      val children = Graphs.successorListOf(graph, current)
      val it = children.iterator()
      while (it.hasNext) {
        val child = it.next()
        if (child.semi == 0) {
          childToParent.put(child, current)
          stack.push(child)
        }
      }
    }

    val buckets = new SetMultimap[DominatorTreeVertex, DominatorTreeVertex]()
    val dominator = new mutable.HashMap[DominatorTreeVertex, DominatorTreeVertex]()

    for (i <- vertex.length - 1 to 2 by -1) {
      val current = vertex(i);
      val parentIter = Graphs.predecessorListOf(graph, current).iterator();
      while(parentIter.hasNext) {
      val parent = parentIter.next()
        val u = eval(parent);
        if(u.semi < current.semi) {
          current.semi = u.semi
        }
        buckets.add(vertex(current.semi), current);
        link(childToParent.get(current).get,current);
        val set = buckets.get(childToParent.get(current).get)
        for(v <- set) {
          val u = eval(v);
          val dom = if( u.semi < v.semi) {
            u
          } else {
            childToParent.get(current).get
          }
          dominator.put(v,dom)
        }
        set.clear()
      }
    }
    for (i <-2 until vertex.length  ) {
      val current = vertex(i);
      if(dominator.get(current).get != vertex(current.semi)) {
        dominator.put(current, dominator.get(dominator.get(current).get).get)
      }
    }


  }


  def eval(vertex: DominatorTreeVertex): DominatorTreeVertex = {
    if (!forest.vertexSet().contains(vertex)) {
      vertex
    }
    else {
      val parents = Graphs.predecessorListOf(forest, vertex);
      if (parents.size() == 0) {
        vertex;
      } else {
        var minimum = vertex;
        var current = minimum;
        var previous = Graphs.predecessorListOf(forest, vertex)
        while (!previous.isEmpty) {
          current = previous.get(0)
          if (current.semi < minimum.semi) {
            minimum = current;
          }
          previous = Graphs.predecessorListOf(forest, current)
        }
        minimum
      }
    }
  }

  def link(parent: DominatorTreeVertex, child: DominatorTreeVertex): Unit = {
    forest.addVertex(parent)
    forest.addVertex(child)
    forest.addEdge(parent, child);
  }


}
