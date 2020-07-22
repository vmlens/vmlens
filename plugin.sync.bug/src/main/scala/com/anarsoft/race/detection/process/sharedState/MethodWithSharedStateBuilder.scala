package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._
import scala.collection.mutable.HashSet


class MethodWithSharedStateBuilder(val methodOrdinal: MethodOrdinal, val group: Int,val isThreadSafe : Boolean ,val isStateless : Boolean, 
    val stateNotFiltered : Boolean, val fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade)  extends Equals with MethodWithXBuilder[MethodWithSharedStateBuilder] {

  val stackTraceOrdinalSet = new HashSet[StackTraceOrdinal]
  val childState = new HashSet[MethodWithSharedStateBuilder];
  
  val memoryAggregateSet = new HashSet[Int]
  
  
  def addChildState(child : MethodWithSharedStateBuilder)
  {
    childState.add(child);
  }
  

  def fill( stackTraceGraph: StackTraceGraph) {

    for (s <- stackTraceOrdinalSet) {
      s.foreachMemoryAggregate(stackTraceGraph, x => {
        
       val aggregate =  fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(x)
        
       
       if( ! aggregate.isReadOnly() )
       {
         
         memoryAggregateSet.add(x) 
       }
       
       
      })
      
    }

    for (child <- childState) {
      {
          for( a <- child.memoryAggregateSet )
          {
            memoryAggregateSet.add(a);
          }
          
          
      }
    }
   
    
  }
  
  
  def create(startIndex : Int) =
  {
    
    // s MethodWithSharedState(val methodOrdinal: MethodOrdinal, val group: Int,val stackTraceOrdinalSet : Set[StackTraceOrdinal], val memoryAggregateSet  : Set[Int])
    new MethodWithSharedState(methodOrdinal,group - startIndex,stackTraceOrdinalSet.toSet,isThreadSafe , isStateless, stateNotFiltered);
  }
  
  
  

  def canEqual(other: Any) = {
      other.isInstanceOf[com.anarsoft.race.detection.process.sharedState.MethodWithSharedStateBuilder]
    }

  override def equals(other: Any) = {
      other match {
        case that: com.anarsoft.race.detection.process.sharedState.MethodWithSharedStateBuilder => that.canEqual(MethodWithSharedStateBuilder.this) && methodOrdinal == that.methodOrdinal
        case _ => false
      }
    }

  override def hashCode() = {
      val prime = 41
      prime + methodOrdinal.hashCode
    }

 
  
    def builder() = this;
  
  
  
  
}
