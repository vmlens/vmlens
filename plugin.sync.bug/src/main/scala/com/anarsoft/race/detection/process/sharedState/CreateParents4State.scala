package com.anarsoft.race.detection.process.sharedState

import  com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class CreateParents4State extends CreateParents[ParentMethodBuilderState]  {
  
   def includeStart() = false;
  
  
   def createBuilder( methodOrdinal: MethodOrdinal ,  reverseGroup : Int, stackTraceOrdinal: StackTraceOrdinal, stackTraceGraph: StackTraceGraph ) = new ParentMethodBuilderState(methodOrdinal,reverseGroup)
  
   def create(stackTraceOrdinalSet : Set[StackTraceOrdinal], stackTraceGraph : StackTraceGraph) =
   {
     
     val tuple =  createInternal(stackTraceOrdinalSet,stackTraceGraph);
     
     val methodOrdinal2Parent =tuple._2;
     val maxGroup = tuple._1;
     
      val result = new ArrayBuffer[ParentMethod]
    
    for( elem <- methodOrdinal2Parent)
    {
      result.append(  elem._2.create(maxGroup) )
    }
   
    result.sortBy( x => x.group  ).toSeq;
   }
  
  
}