package com.anarsoft.race.detection.model.result

import scala.collection.mutable.ArrayBuffer
import com.vmlens.api.internal.IconRepository
import com.vmlens.api._

class NotStateless(val stackTraceOrdinal : StackTraceOrdinal, val memoryAggregateId : Int) {
  
  def name(modelFacade : ModelFacadeState) = stackTraceOrdinal.nameWithoutBracket(modelFacade.stackTraceGraph) + " <<" + modelFacade.fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(memoryAggregateId).location.getName(modelFacade.fieldAndArrayFacade, modelFacade.stackTraceGraph) + ">>"
  
  
  def children(modelFacade : ModelFacadeState) =
  {
    val childs = new ArrayBuffer[NotStatelessStackTrace]
    var count = 0;
    
    modelFacade.stackTraceGraph.foreachStacktraceOrdinalForMemoryAggregate(memoryAggregateId,  s =>
      {
        childs.append( new NotStatelessStackTrace(count, s) );
        
        count = count + 1;
        
      }
       
    )
    
    childs;
    
  }
  
  
   def icon(modelFacade : ModelFacadeState)  =IconRepository.getImageForField(   new  MemoryAccessType( MemoryAccessType.IS_READ_WRITE ) ,false , false , true  )
  
}