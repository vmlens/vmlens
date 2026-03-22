package com.anarsoft.race.detection.report.dominatortree

import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot, VertexState}
import com.anarsoft.race.detection.report.description.DescriptionContext
import com.anarsoft.race.detection.util.Stack
import com.vmlens.report.dominatortree.UIReverseCallTree
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import java.util
import scala.collection.mutable


class CallTreeTraversal(val graph: Graph[DominatorTreeVertex,DefaultEdge],
                        val root: VertexRoot,
                        val descriptionContext: DescriptionContext)  {

  val result = new util.LinkedList[UIReverseCallTree]
  val levelToCSS = new LevelToCSS()
  
   def traverse(start : VertexState ): Unit = {
     val stack = Stack[CallTreeTraversalFrame]
     stack.push(new CallTreeTraversalFrame(start,0))
     val visited = mutable.HashSet[DominatorTreeVertex]()
     
   while(stack.nonEmpty) {
     val current = stack.pop()
     if(current.level != 0) {
       addToReport(current);
     }

     // Skip already visited nodes
     if (!visited.contains(current.node)) {
       visited.add(current.node)
       // Expand upwards unless root is reached
       if (current.node != root) {
         val iter = graph.incomingEdgesOf(current.node).iterator;
         while(iter.hasNext){
           val edgeToParent = iter.next()
           stack.push( new CallTreeTraversalFrame(graph.getEdgeSource(edgeToParent), current.level + 1));
         }
       }
     }
   }
  }
  
  private def addToReport(frame : CallTreeTraversalFrame) : Unit = {
    result.add(new UIReverseCallTree(frame.node.getLabel(descriptionContext),levelToCSS.getCss(frame.level) ))
  }

}
