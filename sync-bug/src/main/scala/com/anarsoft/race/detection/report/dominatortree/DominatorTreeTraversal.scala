package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTree, DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.util.Stack
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.{Graph, Graphs}


class DominatorTreeTraversal {

  def traverse(reportCallback : ReportCallback, 
               graph : Graph[DominatorTreeVertex,DefaultEdge],
               root : VertexRoot ) : Unit = {
    val stack = new  Stack[Frame]
    stack.push(Frame(root, None, 0))

    while (!stack.isEmpty) {
      val frame = stack.pop()
      val parent = frame.node.addToReport(frame.parent, frame.level, reportCallback);
      val children = Graphs.successorListOf(graph, frame.node)
      val it = children.iterator()
      while (it.hasNext) {
        val child = it.next()
        stack.push(Frame(child, Some(parent), frame.level + 1))
      }
    }

  }
  
}
