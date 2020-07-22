package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet

trait MethodWithXBuilder[BUILDER] {
  
  def stackTraceOrdinalSet : HashSet[StackTraceOrdinal];
//  def addChildState(childState : BUILDER );
  
  def group() : Int;
//  def fill( stackTraceGraph: StackTraceGraph)
  
  
  def builder() : BUILDER;
  
  
}