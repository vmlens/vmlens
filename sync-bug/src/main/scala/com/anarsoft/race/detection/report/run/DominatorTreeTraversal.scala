package com.anarsoft.race.detection.report.run

import com.anarsoft.race.detection.dominatortree.DominatorTreeVertex
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIDominatorTreeElement
import org.jgrapht.{Graph, Graphs}
import org.jgrapht.graph.DefaultEdge

import java.util

class DominatorTreeTraversal {

  def traverse(graph: Graph[DominatorTreeVertex,DefaultEdge], 
               root: DominatorTreeVertex, 
               descriptionContext: DescriptionContext):  util.LinkedList[UIDominatorTreeElement] = {

    val levelToCSS = new LevelToCSS();
    val result = new util.LinkedList[UIDominatorTreeElement]
    val stack = new  Stack[Frame]
    stack.push(Frame(root, None, 0))

    while (!stack.isEmpty) {
      val frame = stack.pop()

      val parent = frame.node.addToReport(frame.parent, frame.level, descriptionContext, levelToCSS, result);
      
      val children = Graphs.successorListOf(graph, frame.node)
      val it = children.iterator()
      while (it.hasNext) {
        val child = it.next()
        stack.push(Frame(child, Some(parent), frame.level + 1))
      }
    }
    result;
  }
  
  
}
