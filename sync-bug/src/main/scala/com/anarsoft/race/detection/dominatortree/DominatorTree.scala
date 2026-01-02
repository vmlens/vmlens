package com.anarsoft.race.detection.dominatortree

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultEdge

class DominatorTree(val graph : Graph[DominatorTreeVertex,DefaultEdge], 
                    val root : VertexRoot,
                    val callGraph : Graph[DominatorTreeVertex,DefaultEdge] ) {

}
