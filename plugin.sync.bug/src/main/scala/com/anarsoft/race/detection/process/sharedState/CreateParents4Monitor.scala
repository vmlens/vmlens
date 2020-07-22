package com.anarsoft.race.detection.process.sharedState

import  com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class CreateParents4Monitor extends CreateParents[ParentMethodBuilderMonitor]  {
  
  
  def includeStart() = false;
  
   def createBuilder( methodOrdinal: MethodOrdinal ,  reverseGroup : Int, stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph )  = new ParentMethodBuilderMonitor(methodOrdinal,reverseGroup,stackTraceOrdinal.hasMonitor(stackTraceGraph))
  
   def create(stackTraceOrdinalSet : Set[StackTraceOrdinal], stackTraceGraph : StackTraceGraph) =
   {
     
     val tuple =  createInternal(stackTraceOrdinalSet,stackTraceGraph);
     
     val methodOrdinal2Parent =tuple._2;
     val maxGroup = tuple._1;
     
      val result = new ArrayBuffer[ParentMethodWithMonitor]
    
    for( elem <- methodOrdinal2Parent)
    {
      result.append(  elem._2.create(maxGroup) )
    }
   
    result.sortBy( x => x.group  ).toSeq;
   }
  
  
}