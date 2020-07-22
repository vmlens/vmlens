package com.anarsoft.race.detection.process.aggregate

class AggregateCollectionWithoutAggregateInfo[ID,EVENT]  extends  AggregateCollection[ID,EVENT]  {
  
   def onNewAggregate( id : ID , event : EVENT  )
   {
     
   }
  
}

object AggregateCollectionWithoutAggregateInfo
{
  def getMethodAggregateId4Array(contextBuildAggregate : ContextBuildAggregate) = contextBuildAggregate.methodAggregateId4Array;
}