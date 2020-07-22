package com.anarsoft.race.detection.process.aggregate

import com.anarsoft.race.detection.model.result.LocationInClass
import com.anarsoft.race.detection.model.result.MemoryAccessAggregate
import com.anarsoft.race.detection.process.volatileField.ContextVolatileField
import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.model.result.MethodOrdinalAndPosition
import com.anarsoft.race.detection.model.graph._;

trait ContextBuildAggregate  {
  
  
  var stackTraceOrdinalId2LocationInClass : AggregateCollectionWithAggregateInfo[ID4AggregateStackTraceOrdinal,WithLocationInClass,MemoryAccessAggregate] = null;
  var methodAggregateId4Array : AggregateCollectionWithoutAggregateInfo[MethodOrdinalAndPosition,Any]  = null;
  
  
 var  methodId2Ordinal :  ModelKey2OrdinalMap[Int] = null;
  
  
  def initializeContextBuildAggregate()
  {
    stackTraceOrdinalId2LocationInClass = new AggregateCollectionWithAggregateInfo[ID4AggregateStackTraceOrdinal,WithLocationInClass,MemoryAccessAggregate](  e => 
      {
        new MemoryAccessAggregate( e.getLocationInClass() ,   e.operation)
      } ,
      ( event , aggregate ) => {   
        
      
        aggregate.operation  = aggregate.operation  | event.operation ;
        
        
      }
       ); 
    methodAggregateId4Array = new  AggregateCollectionWithoutAggregateInfo[MethodOrdinalAndPosition,Any] ();
    methodId2Ordinal =  ModelKey2OrdinalMap[Int]();

  }
  
  
}