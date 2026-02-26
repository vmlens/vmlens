package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.api.automatic.internal.AutomaticTestTypes
import com.vmlens.preanalyzed.model.MethodToMethodType
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.jgrapht.traverse.BreadthFirstIterator
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{NON_BLOCKING_WITH_FILTER_WRITE, NON_BLOCKING_WITH_FILTER_READ}

import scala.collection.mutable.ArrayBuffer

class AutomaticTestMethod(private val automaticTestType : Int,
                          private val graph : DefaultDirectedGraph[AutomaticTestMethodVertex, DefaultEdge],
                          private val rootSet : Set[AutomaticTestMethodVertex]) {

  def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    val iter = graph.vertexSet().iterator();
    while(iter.hasNext) {
      iter.next().addToNeedsDescription(callback);
    }

  }

  def buildPreAnalyzedClasses(className: String,
                              context: DescriptionContext,
                              methodResult : ArrayBuffer[MethodToMethodType]): Unit = {
    var found = false;
    val iter = rootSet.iterator
    while(iter.hasNext && ! found) {
      val graphIter = new BreadthFirstIterator(graph, iter.next())
      while(graphIter.hasNext) {
        val vertex = graphIter.next();
        if( context.methodName(vertex.methodId).startsWith(className) ) {
          context.methodDescription(vertex.methodId) match {

            case None =>{
              
            }

            case Some(x) => {
              val strategy = if(automaticTestType == AutomaticTestTypes.READ) {
                NON_BLOCKING_WITH_FILTER_READ
              } else {
                NON_BLOCKING_WITH_FILTER_WRITE
              }
              methodResult.append(new MethodToMethodType(x._2.name(),x._2.desc(),strategy));
            }

          }
          found = true;
        }
      }

    }

  }


  

}
