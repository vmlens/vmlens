package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.createdominatortreeevent.{CreateDominatorTreeEvent, CreateDominatorTreeEventOrdering}
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexRoot}
import com.anarsoft.race.detection.rundata.RunData
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable


class CreateGraphFromEvents(val root: VertexRoot) {

  val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]()

  val graph = new DefaultDirectedGraph[DominatorTreeVertex, DefaultEdge](classOf[DefaultEdge]);

  def process(runData: RunData): Graph[DominatorTreeVertex,DefaultEdge] = {
    runData.methodEventArray.sort(new CreateDominatorTreeEventOrdering());
    graph.addVertex(root)

    var stack: Option[CreateGraphStack] = None;

    for (event <- runData.methodEventArray) {
      stack match {
        case  None => {
          val x = new CreateGraphStack(root, event.threadIndex);
          addEvent(x,event);
          stack = Some(x)
        }
        case Some(y) => {
          if(y.threadIndex == event.threadIndex) {
            addEvent(y, event);
          }  else {
            val x = new CreateGraphStack(root, event.threadIndex);
            addEvent(x, event);
            stack = Some(x)
          }
        }
      }
    }

    graph;
  }

  private def addEvent(stack : CreateGraphStack, event : CreateDominatorTreeEvent): Unit = {
    event.add(stack,alreadyAdded,graph)
  }

}
