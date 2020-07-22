package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.scheduler.LoopId2Result
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

//import com.anarsoft.race.detection.model.parallize.normalized.ParallizeIdAndStatementList

trait ContextCreateModelFacade {
  
  
   var modelFacade : ModelFacadeAll = null;
  
   def loopId2Result : LoopId2Result;
    
  def raceExceptionSet : HashSet[RaceConditionFoundException];  
    
  def threadNames : ThreadNames;
  def stackTraceGraph : StackTraceGraph;

 
  def  methodId2Ordinal :  ModelKey2OrdinalMap[Int];
  
// def  sharedStateCollection : ArrayBuffer[MethodWithSharedState]
  
//   def fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade;
   

   
   def  deadlocks : HashSet[Deadlock]
  
   def fieldAndArrayFacade : FieldAndArrayPerMethodFacade;
  

   
   def agentLog : ArrayBuffer[String];
   
   
  
   
   
   
}