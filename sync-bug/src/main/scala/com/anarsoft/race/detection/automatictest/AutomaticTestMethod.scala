package com.anarsoft.race.detection.automatictest

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.api.atomic.internal.AutomaticTestTypes
import com.vmlens.preanalyzed.model.{ClassModel, MethodToMethodType}
import org.jgrapht.graph.{DefaultDirectedGraph, DefaultEdge}
import org.jgrapht.traverse.BreadthFirstIterator
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.{ATOMIC_FIELD_WRITE, ATOMIC_FIELD_READ}

import java.io.PrintWriter
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
                              writer: PrintWriter,
                              methodResult : ArrayBuffer[MethodToMethodType]): Unit = {
    var found = false;
    val iter = rootSet.iterator
    while(iter.hasNext && ! found) {
      val graphIter = new BreadthFirstIterator(graph, iter.next())
      while(graphIter.hasNext) {
        val vertex = graphIter.next();
        if( context.methodName(vertex.methodId).startsWith(className) ) {
          writer.print( context.methodName(vertex.methodId) + " "  +automaticTestType )
          context.methodDescription(vertex.methodId) match {

            case None =>{
              
            }

            case Some(x) => {
              val strategy = if(automaticTestType == AutomaticTestTypes.READ) {
                ATOMIC_FIELD_READ
              } else {
                ATOMIC_FIELD_WRITE
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
