package com.anarsoft.race.detection.createdominatortreeevent

import com.anarsoft.race.detection.createdominatortree.CreateGraphStack
import com.anarsoft.race.detection.dominatortree.{DominatorTreeVertex, VertexAtomicNonBlockingOrVolatile}
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.DominatorMemoryAccessKey
import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

import scala.collection.mutable

class CreateDominatorTreeContext(val stack : CreateGraphStack,
                                 val alreadyAdded : mutable.HashSet[DominatorTreeVertex],
                                 val memoryKeyToVertex : mutable.HashMap[DominatorMemoryAccessKey,VertexAtomicNonBlockingOrVolatile],
                                 val graph : Graph[DominatorTreeVertex,DefaultEdge], 
                                 val objectHashCodeToInt :  ObjectHashCodeToInt) {

}
