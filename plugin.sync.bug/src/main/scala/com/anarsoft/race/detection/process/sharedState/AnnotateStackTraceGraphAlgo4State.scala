package com.anarsoft.race.detection.process.sharedState

import com.anarsoft.race.detection.model.result._

import com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap



class AnnotateStackTraceGraphAlgo4State(val fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade,val fieldAndArrayPerMethodFacade : FieldAndArrayPerMethodFacade) extends AnnotateStackTraceGraphAlgo[StackTraceData4State]  {
  
  
  def filter( locationInClass : LocationInClass , stackTraceGraph : StackTraceGraph ) =
  {
    if( locationInClass.getName(fieldAndArrayPerMethodFacade, stackTraceGraph).startsWith("sun.misc") )
    {
      true
    }
    else
    {
      false
    }
    
  }
  
  
  
  def create(ordinal : Int,stackTraceGraph: StackTraceGraph, packageName2Ordinal : ModelKey2OrdinalMap[String]) =
  {
    val methodModel = stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(ordinal) );
    
    
     val data=  new StackTraceData4State(methodModel);
     
  
        stackTraceGraph.foreachMemoryAggregate(new StackTraceOrdinal(ordinal), x =>
       {
           val aggregate =  fieldAndArrayPerStackTraceFacade.stacktraceAggreagateOrdinal2LocationInClass(x)
           if( ! aggregate.isReadOnly() &&  ! filter( aggregate.location ,stackTraceGraph  ) ) //  && ! aggregate.sortable
           {
      
                   data.memoryAggregateIdSet.add(x);
           }
       
       }
     )
     
    
     
     
     data.addPackageId(methodModel,packageName2Ordinal);
     
     
     
     data
     
  }
  
  
  def addFromChild(parent : StackTraceData4State,child : StackTraceData4State)
  {
    
      addAll2RoaringBitmap( parent.memoryAggregateIdSet , child.memoryAggregateIdSet)
      addAll2RoaringBitmap( parent.packageIdSet , child.packageIdSet)
   
  }
  
  
  
}