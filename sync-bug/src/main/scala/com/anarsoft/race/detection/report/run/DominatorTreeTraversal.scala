package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.util.Stack

import org.jgrapht.{Graph, Graphs}
import org.jgrapht.graph.DefaultEdge


class DominatorTreeTraversal {

  def traverse(context : DominatorTreeTraversalContext) : Unit = {

   
    val stack = new  Stack[Frame]
    stack.push(Frame(context.dominatorTree.root, None, 0))

    while (!stack.isEmpty) {
      val frame = stack.pop()

      val parent = frame.node.addToReport(frame.parent, frame.level, context);
      
      val children = Graphs.successorListOf(context.dominatorTree.graph, frame.node)
      val it = children.iterator()
      while (it.hasNext) {
        val child = it.next()
        stack.push(Frame(child, Some(parent), frame.level + 1))
      }
    }

  }
  
  
}
