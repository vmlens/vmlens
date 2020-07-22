package com.anarsoft.race.detection.model.result


import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.result._
import  com.anarsoft.race.detection.model.description._
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import java.util.ArrayList
import com.anarsoft.race.detection.model.scheduler.LoopId2Result



class ModelFacadeAll( val stackTraceGraph : StackTraceGraph, 
    val races :  HashSet[RaceCondition],val threadNames : ThreadFacade,
    val fieldAndArrayFacade : FieldAndArrayPerMethodFacade,
    val deadlocks : HashSet[Deadlock],
    val loopId2Result : LoopId2Result,
    val agentLog : ArrayBuffer[String]
) extends ModelFacade
{
  
  
  def hasIssues() =
  {
    if( ! races.isEmpty )
    {
      true;
    }
    else  if( ! deadlocks.isEmpty )
    {
      true;
    }
    else
    {
      false;
    }
    
  }
  
  
  val   ownerId2Name = new  HashMap[Int,String]
  
//  def getFieldModel( ordinal : Int ) = fieldAndArrayFacade.fieldOrArrayAggregateOrdinal2Model(ordinal);
  
  
  
  
  
  
  
  
  
}