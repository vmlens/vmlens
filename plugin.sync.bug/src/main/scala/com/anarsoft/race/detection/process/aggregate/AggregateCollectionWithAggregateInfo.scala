package com.anarsoft.race.detection.process.aggregate


import scala.collection.mutable.ArrayBuffer

class AggregateCollectionWithAggregateInfo[ID,-EVENT,AGGREGATE]( val create : EVENT => AGGREGATE , val setValues : ( EVENT , AGGREGATE ) => Unit  )
   extends  AggregateCollection[ID,EVENT]  {
  
  val aggregateId2Aggregate = new ArrayBuffer[AGGREGATE];
  
  
  def onNewAggregate( id : ID , event : EVENT  )
  {
    aggregateId2Aggregate.append(  create(event)  )
  }
  
  
 def setValuesInAgggregate(event : EVENT, aggregateId : Int)
 {
   setValues(  event ,  aggregateId2Aggregate(aggregateId) );
 }
  
  
  
}


object AggregateCollectionWithAggregateInfo
{
  
  def getStackTraceOrdinalId2LocationInClass(contextBuildAggregate : ContextBuildAggregate) = contextBuildAggregate.stackTraceOrdinalId2LocationInClass;
  
  
}