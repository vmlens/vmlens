package com.anarsoft.race.detection.process


import com.anarsoft.race.detection.model.graph._;

trait ContextStackTraceGraphBuilder {
  var  stackTraceGraphBuilder : StackTraceGraphBuilder = null;
  def  methodId2Ordinal :  ModelKey2OrdinalMap[Int];
  
  
  def initializeContextStackTraceGraphBuilder()
  {
    
     stackTraceGraphBuilder = StackTraceGraphBuilder(methodId2Ordinal); 
  }
  
}