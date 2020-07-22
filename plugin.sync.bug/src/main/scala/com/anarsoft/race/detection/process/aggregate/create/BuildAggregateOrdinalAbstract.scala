package com.anarsoft.race.detection.process.aggregate.create

import scala.collection.mutable.HashMap;



class BuildAggregateOrdinalAbstract {
  
  
   def createAggregateId2AggregateOrdinal(in : BuildAggregateOrdinalIn,  currentOrdinalId :  MutableInt) =
  {
    val aggregateId2OrdinalMap = BuildOrdinal4SameAggregate.create(in.sameAggregateId,currentOrdinalId);
    
    
    val aggregateId2Ordinal = Array.ofDim[Int](in.currentAggregateId);
    
    for( i <- 0 until  in.currentAggregateId )
    {
      
      val aggreagetOrdinal = 
      
      
      aggregateId2OrdinalMap.get(i) match
      {
        
        case None =>
          {
           currentOrdinalId.getAndIncrement();
           
           
            
          }
        
        case Some(x) =>
          {
            x.ordinal;
          }
        
      }
      
      
      
     aggregateId2Ordinal(i) = aggreagetOrdinal;
    }
    
    
    aggregateId2Ordinal;
  }
  
  
   
   
  
  
}