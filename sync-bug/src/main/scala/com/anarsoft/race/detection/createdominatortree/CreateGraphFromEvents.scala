package com.anarsoft.race.detection.createdominatortree

import com.anarsoft.race.detection.createdominatortreeevent.{BuildDominatorTreeContext, CreateDominatorTreeContext, CreateDominatorTreeEvent, CreateDominatorTreeEventOrdering, ObjectHashCodeToInt}
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexState, VertexRoot, NormalizeVertex}
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import com.anarsoft.race.detection.rundata.RunData
import org.jgrapht.Graph
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer


class CreateGraphFromEvents(val normalizeVertex: NormalizeVertex) {

  val alreadyAdded = new mutable.HashSet[DominatorTreeVertex]()
 
  val memoryKeyToVertex =  new mutable.HashMap[DominatorMemoryAccessKey,VertexState]()
  val objectHashCodeToInt = new ObjectHashCodeToInt();

  /**
   * adds elements from methodEventArray and interleave actions to CreateDominatorTreeEventList
   * Then sort by threadId and dominatorTreeCounter 
   * Then process using stack
   *
   * @param runData
   * @return
   */
  def process(runData: RunData): Graph[DominatorTreeVertex, DefaultEdge] = {
    val eventList = new ArrayBuffer[CreateDominatorTreeEvent]
    for (event <- runData.methodEventArray) {
      eventList.append(event);
    }
    
    val context = new BuildDominatorTreeContext(eventList);
    for(interleaveGroup <- runData.interLeaveElements) {
      interleaveGroup.addToBuildDominatorTreeContext(context)
    }

    eventList.sortInPlace()(new CreateDominatorTreeEventOrdering());

    var stack: Option[CreateGraphStack] = None;

    for (event <- eventList) {
      stack match {
        case None => {
          val x = new CreateGraphStack(normalizeVertex, event.threadIndex);
          addEvent(x, event);
          stack = Some(x)
        }
        case Some(y) => {
          if (y.threadIndex == event.threadIndex) {
            addEvent(y, event);
          } else {
            val x = new CreateGraphStack(normalizeVertex, event.threadIndex);
            addEvent(x, event);
            stack = Some(x)
          }
        }
      }
    }
    normalizeVertex.graph;
  }

  private def addEvent(stack: CreateGraphStack, event: CreateDominatorTreeEvent): Unit = {
    val context = new CreateDominatorTreeContext(stack, memoryKeyToVertex, objectHashCodeToInt);
    event.add(context)
  }

}
