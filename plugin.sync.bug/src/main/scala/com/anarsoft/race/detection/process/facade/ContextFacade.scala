package com.anarsoft.race.detection.process.facade

import com.anarsoft.race.detection.model.result.MemoryAccessAggregate
import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.process.aggregate._
import com.anarsoft.race.detection.model.result.FieldAndArrayPerStackTraceFacade

trait ContextFacade {
  
   def stackTraceGraphBuilder : StackTraceGraphBuilder;
   
   def stackTraceOrdinalId2LocationInClass : AggregateCollectionWithAggregateInfo[ID4AggregateStackTraceOrdinal,WithLocationInClass,MemoryAccessAggregate];
  
  
   var fieldAndArrayPerStackTraceFacade : FieldAndArrayPerStackTraceFacade = null;
   
}