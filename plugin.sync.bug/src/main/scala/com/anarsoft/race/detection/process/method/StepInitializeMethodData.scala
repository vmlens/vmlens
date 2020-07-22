package com.anarsoft.race.detection.process.method


import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.method.StackTraceElement2Id;
import com.anarsoft.race.detection.process.workflow.SingleStep
import scala.collection.mutable.HashSet
import java.util.ArrayList;
import com.anarsoft.race.detection.model.result.MethodOrdinal

class StepInitializeMethodData extends SingleStep[ContextMethodData]  {
  
  def execute(context : ContextMethodData)
  {
     
   context.methodFlow  = new MethodFlow();
   context.stackTraceTree  = new StackTraceTree();
   context.stackTraceElement2Id = new StackTraceElement2Id();
   
   context.threadId2StackTraceForestPerThreadBuilder  = new HashMap[Long,StackTraceForestPerThreadBuilder]();
    
 //  context.methodIdAndThreadIdSet = HashSet[MethodIdAndThreadId]();
  
  
   
   
  }
  
  
  def desc = "";
  
}