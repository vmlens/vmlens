package com.anarsoft.race.detection.model.result

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer



trait ModelFacade {
  
  def  stackTraceGraph : StackTraceGraph;
  def  threadNames : ThreadFacade;
  
  
   def hasIssues() : Boolean;
   
   def  ownerId2Name : HashMap[Int,String];
   
   def agentLog : ArrayBuffer[String];
  
}