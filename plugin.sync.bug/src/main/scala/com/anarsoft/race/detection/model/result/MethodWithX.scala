package com.anarsoft.race.detection.model.result

import scala.collection.mutable.HashSet


trait MethodWithX {
  
  def  stackTraceOrdinalSet : Set[StackTraceOrdinal];
  
  
   def threadCount(stackTraceGraph : StackTraceGraph) =
  {
    val threadOrdinalSet = new HashSet[Int]
    
    for( s <- stackTraceOrdinalSet)
    {
      stackTraceGraph.foreachThreadOrdinal(  s , t => threadOrdinalSet.add(t) );    
    }
    
    threadOrdinalSet.size;
    
    
  }
  
  
  
  def threadOrdinalSet(stackTraceGraph : StackTraceGraph) =
  {
    val threadOrdinalSet = new HashSet[Int]
    
    for( s <- stackTraceOrdinalSet)
    {
      stackTraceGraph.foreachThreadOrdinal(  s , t => threadOrdinalSet.add(t) );
      
      
      
    }
    
    threadOrdinalSet;
  }
  
  
  
  
}