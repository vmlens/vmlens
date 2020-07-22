package com.anarsoft.race.detection.process.sharedState

import  com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

abstract class CreateParents[BUILDER <:  ParentMethodBuilder] {
  
  
  def createBuilder( methodOrdinal: MethodOrdinal ,  reverseGroup : Int, stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph ) : BUILDER;
 
  def includeStart() : Boolean;
  
  
  
  def createInternal(stackTraceOrdinalSet : Set[StackTraceOrdinal], stackTraceGraph : StackTraceGraph) =
  {
    val methodOrdinal2Parent = new HashMap[MethodOrdinal,BUILDER]; 
    var maxGroup = 0;
    
    for(  startNode <-   stackTraceOrdinalSet )
    {
   
        var reverseGroup = 0;
        var currentChild = startNode;
        var keepGoing = true;
        
        if(includeStart())
        {
          val parent = new StackTraceOrdinal(currentChild.ordinal);
          
          val methodOrdinal = parent.getMethodOrdinal(stackTraceGraph); ;
               
               val parentBuilder = methodOrdinal2Parent.getOrElseUpdate(  methodOrdinal ,createBuilder(methodOrdinal , reverseGroup , parent ,stackTraceGraph  )  )
               
               parentBuilder.stackTraceOrdinalSet.add(  parent );     
               
                reverseGroup = reverseGroup + 1;
        }
        
        
        while( keepGoing  )
        {
          
           keepGoing = false;
           
           maxGroup = Math.max(maxGroup, reverseGroup)
          
           stackTraceGraph.onParent(  currentChild.ordinal ,  parent =>
             {
               
               val methodOrdinal = parent.getMethodOrdinal(stackTraceGraph);
               
               val parentBuilder = methodOrdinal2Parent.getOrElseUpdate(  methodOrdinal ,createBuilder(methodOrdinal , reverseGroup , parent ,stackTraceGraph  )  )
               
               parentBuilder.stackTraceOrdinalSet.add(  parent );       
               currentChild = parent;
                keepGoing = true;
             }

           )
           
           reverseGroup = reverseGroup + 1;
           
        }
         
      
    }
    
   Tuple2(maxGroup, methodOrdinal2Parent);
    
   
    
  }
  
  
  
}