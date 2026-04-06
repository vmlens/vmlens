package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexState}
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

class CreateDominatorTreeContext(val stack : CreateGraphStack,
                                 val memoryKeyToVertex : mutable.HashMap[DominatorMemoryAccessKey,VertexState],
                                 val objectHashCodeToInt :  ObjectHashCodeToInt) {

}
