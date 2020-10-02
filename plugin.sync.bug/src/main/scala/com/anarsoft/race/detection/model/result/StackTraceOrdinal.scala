package com.anarsoft.race.detection.model.result



case class StackTraceOrdinal ( val ordinal : Int )  {
  
   def name(stackTraceGraph : StackTraceGraph) =
  {
     val m=   stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(this);
      m.getFullName();
  }
   
  def nameWithBoldName(viewTyp : ModelFacadeAll) =
  {
    val m=   viewTyp.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(this);
      m.getFullNameWithBoldName();
  }
  
  
  def lineNumber( stackTraceGraph : StackTraceGraph) =
  {
     val m=   stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(this);
      m.getLineNumber();
  }
  
   
   
   def nameWithoutBracket(stackTraceGraph : StackTraceGraph) =
   {
      val m=   stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(this);
      m.getFullNameWithoutBracket();
   }
   

 
  
  def getMethodOrdinal(stackTraceGraph : StackTraceGraph) =
  {
    stackTraceGraph.getMethodModelOrdinalForStackTraceNodeOrdinal(this);
  }
  
  
  def foreachMemoryAggregate( stackTraceGraph : StackTraceGraph ,f : Int => Unit )
  {
    stackTraceGraph.foreachMemoryAggregate(this, f);
    
    
  }
  
  
  def hasMonitor( stackTraceGraph : StackTraceGraph) = stackTraceGraph.hasMonitor(this);
  
  
  
  
  def getMethodDescription(stackTraceGraph : StackTraceGraph)  =
   getMethodOrdinal(stackTraceGraph).methodDescription(stackTraceGraph);
      
  
  
  
   
   
}

