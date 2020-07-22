package com.anarsoft.race.detection.process.mode.state


import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.model.result.MethodWithSharedState
import scala.collection.mutable.HashSet;
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.model.description.ThreadNames;
import com.anarsoft.race.detection.model.result._;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model._;
import com.anarsoft.race.detection.model.graph._;

import com.anarsoft.race.detection.process.partialOrder.RaceConditionFoundException
import java.util.ArrayList


import com.anarsoft.race.detection.model.result.MethodWithSharedState
import com.anarsoft.race.detection.process.nonVolatileField.NonVolatileFieldId


trait ContextCreateModelFacadeState {
  
  var modelFacadeState : ModelFacadeState = null;
  
    def  ownerId2Name : HashMap[Int,String]
 def  sharedStateCollection : ArrayBuffer[MethodWithSharedState]
 
   def threadNames : ThreadNames;
  def stackTraceGraph : StackTraceGraph;
  
     def fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade;
 
 
    def fieldAndArrayFacade : FieldAndArrayPerMethodFacade;
    
    
  
     
    def  notStateless : ArrayBuffer[NotStateless];
  
    
    def stackTraceGraphStateAnnotation : StackTraceGraphStateAnnotation ;
    
    
     def agentLog : ArrayBuffer[String];
     
     
   
}