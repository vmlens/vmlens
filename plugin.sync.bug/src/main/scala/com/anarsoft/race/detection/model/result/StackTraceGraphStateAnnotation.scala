package com.anarsoft.race.detection.model.result

import com.netflix.nfgraph.compressed.NFCompressedGraph
import scala.collection.mutable.HashSet

class StackTraceGraphStateAnnotation(val graph : NFCompressedGraph, val  packageArray : Array[String] ) extends Graph with StackTraceGraphPackageAnnotation {
  
  
  def memoryAggregateSet(stackTraceOrdinalSet : Set[StackTraceOrdinal]) =
  {
     val result =    new HashSet[Int]
     
     for(  stackTraceOrdinal<- stackTraceOrdinalSet )
     {
      foreachOrdinal( StackTraceGraphStateAnnotation.NODE_STACK_TRACE, stackTraceOrdinal.ordinal  , StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_MEMORY_AGGREGATE ,     
   x => {
   
     result.add(x)
     
   }
  );
     }
     
     result;
  }
  
  
  def foreachRootPackageName( s : StackTraceOrdinal , f : (String) => Unit  ) =
  {
      foreachOrdinal( StackTraceGraphStateAnnotation.NODE_STACK_TRACE, s.ordinal  , StackTraceGraphStateAnnotation.REL_STACK_TRACE_2_PACKAGE ,     
   x => {
   
       f(packageArray(x))
     
   }
  );
    
    
  }
  
  
}

object StackTraceGraphStateAnnotation
{
    val NODE_STACK_TRACE      = "stackTrace"; 
    val NODE_MEMORY_AGGREGATE = "memoryAggregate";
    val NODE_ROOT_PACKAGE     = "package"
    
    val REL_STACK_TRACE_2_MEMORY_AGGREGATE = "stackTrace2memoryAggregate"
    val REL_STACK_TRACE_2_PACKAGE = "stackTrace2package"
}