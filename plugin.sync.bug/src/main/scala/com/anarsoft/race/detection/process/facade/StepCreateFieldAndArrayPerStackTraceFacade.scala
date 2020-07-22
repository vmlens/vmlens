package com.anarsoft.race.detection.process.facade

import  com.anarsoft.race.detection.process.aggregate.create._
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.model.result.LocationInClass
import  com.anarsoft.race.detection.model.result.StackTraceGraph
import  com.anarsoft.race.detection.model.result.FieldAndArrayPerStackTraceFacade
import com.anarsoft.race.detection.model.result.MemoryAccessAggregate
/**
 * 
 * stacktrace ordinal zu shared state 
 * stacktrace ordinal zu monitor
 * 
 * 
 * 
 */

class StepCreateFieldAndArrayPerStackTraceFacade extends SingleStep[ContextFacade]  {
  
  
   def execute(context : ContextFacade)
  {
     
       
    val buildAggregateIn =   new BuildAggregateOrdinalIn(context.stackTraceOrdinalId2LocationInClass.sameAggregateId, 
          context.stackTraceOrdinalId2LocationInClass.currentAggregateId)
     
      
     val buildAggregateOrdinal = new BuildAggregateOrdinalAbstract();
     val  currentOrdinalId = new MutableInt(0);
    
        
        
    
   val aggregateId2Ordinal =    buildAggregateOrdinal.createAggregateId2AggregateOrdinal(buildAggregateIn, currentOrdinalId)    
    
   
   val  stacktraceAggreagateOrdinal2LocationInClass  = Array.ofDim[MemoryAccessAggregate]( currentOrdinalId.value )
   
   // um location in class zu setzen
   
   var index = 0;   
      
   for( elem <- context.stackTraceOrdinalId2LocationInClass.aggregateId2Aggregate )
   {
     val aggregateOrdinal = aggregateId2Ordinal(index);
     
     stacktraceAggreagateOrdinal2LocationInClass(aggregateOrdinal) = elem;
     
     
     
     index = index + 1;
   }
   
   
   
   
   for( elem <- context.stackTraceOrdinalId2LocationInClass.id4Aggregate2AggregateId )
   {
       val aggregateOrdinal = aggregateId2Ordinal(elem._2);
       context.stackTraceGraphBuilder.nfsBuildGraph.addConnection(StackTraceGraph.NODE_SHARED_STATE, aggregateOrdinal, StackTraceGraph.REL_SHARED_STATE_2_STACK_TRACE, elem._1.stackTraceOrdinal);
       context.stackTraceGraphBuilder.nfsBuildGraph.addConnection( StackTraceGraph.NODE_STACK_TRACE ,   elem._1.stackTraceOrdinal , StackTraceGraph.REL_STACK_TRACE_2_SHARED_STATE   , aggregateOrdinal);
   }
   
   context.fieldAndArrayPerStackTraceFacade = new FieldAndArrayPerStackTraceFacade (stacktraceAggreagateOrdinal2LocationInClass);
   
      
  }
}