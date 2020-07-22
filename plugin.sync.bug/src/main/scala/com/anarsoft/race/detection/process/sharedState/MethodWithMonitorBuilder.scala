package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet

class MethodWithMonitorBuilder(val methodOrdinal: MethodOrdinal, val group: Int,val methodContainsMonitor : Boolean,val isThreadSafe : Boolean) extends MethodWithXBuilder[MethodWithMonitorBuilder] {
  
    val childState = new HashSet[MethodWithMonitorBuilder];
 // var calledMethodContainsMonitor = false;
  val stackTraceOrdinalSet = new HashSet[StackTraceOrdinal]
  
  // MethodWithMonitor(val methodOrdinal: MethodOrdinal, val group: Int,val stackTraceOrdinalSet : Set[StackTraceOrdinal],val methodContainsMonitor : Boolean , val calledMethodContainsMonitor : Boolean)   
  
  def create(startIndex : Int) =
  {
    
    // s MethodWithSharedState(val methodOrdinal: MethodOrdinal, val group: Int,val stackTraceOrdinalSet : Set[StackTraceOrdinal], val memoryAggregateSet  : Set[Int])
    new MethodWithMonitor(methodOrdinal,group - startIndex,stackTraceOrdinalSet.toSet,methodContainsMonitor  , isThreadSafe );
  }
  
//   def addChildState(child : MethodWithMonitorBuilder)
//  {
//    childState.add(child);
//  }
//   
//    def fill( stackTraceGraph: StackTraceGraph) {
//
//  
//    for (child <- childState) {
//      {
//            
//        calledMethodContainsMonitor = calledMethodContainsMonitor | child.methodContainsMonitor;
//        calledMethodContainsMonitor = calledMethodContainsMonitor | child.calledMethodContainsMonitor;
//          
//      }
//    }
//   
//    
//  }
    
     def builder() = this;
   
   
  
}