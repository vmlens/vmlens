package com.anarsoft.race.detection.model.result

import com.netflix.nfgraph.compressed.NFCompressedGraph
import scala.collection.mutable.HashSet

class StackTraceGraphMonitorAnnotation(val graph : NFCompressedGraph, val  packageArray : Array[String] )  extends Graph with StackTraceGraphPackageAnnotation  {
  
  
  
  
  def foreachRootPackageName( s : StackTraceOrdinal , f : (String) => Unit  ) =
  {
      foreachOrdinal( StackTraceGraphMonitorAnnotation.NODE_STACK_TRACE, s.ordinal  , StackTraceGraphMonitorAnnotation.REL_STACK_TRACE_2_PACKAGE ,     
   x => {
   
       f(packageArray(x))
     
   }
  );
    
    
  }
  
  
  def hasMonitor( stackTraceOrdinal : StackTraceOrdinal )  =
  {
     
    graph.getConnection( StackTraceGraphMonitorAnnotation.NODE_STACK_TRACE, stackTraceOrdinal.ordinal, StackTraceGraphMonitorAnnotation.REL_STACK_TRACE_2_HAS_MONITOR) != -1;
    
    
  }
  
}


object StackTraceGraphMonitorAnnotation
{
    val NODE_STACK_TRACE      = "stackTrace"; 
    val NODE_HAS_MONITOR = "hasMonitor";
    val NODE_ROOT_PACKAGE     = "package"
    
    val REL_STACK_TRACE_2_HAS_MONITOR = "stackTrace2memoryAggregate"
    val REL_STACK_TRACE_2_PACKAGE = "stackTrace2package"
}