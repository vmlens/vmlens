package com.anarsoft.race.detection.model.result



import com.anarsoft.race.detection.model.description._
import com.vmlens.api.internal._
import com.vmlens.api._
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashSet
import com.vmlens.api.FieldDescription
import com.vmlens.api.MethodDescription


class FieldAndArrayPerMethodFacade(
    val fieldOrdinal2FieldModelDesc : Array[FieldModel],   val arrayAggregateId2Ordinal : Array[Int] ,
    val arrayOrdinal2Model : Array[ArrayModel] , val id2Name : Array[String]  ) {
  
  
  def getArrayModel( arrayLocationId : Int  ) =
  {
   arrayOrdinal2Model( arrayAggregateId2Ordinal(arrayLocationId) );
  }
  
   def getClassName(ordinal : Int) =
  {
    id2Name(ordinal);
  }
  
  
//  def getFieldOrArrayAggregate( ordinal : Int ) =
//  {
//    
//    
//    
//    val r = aggregateOrdinal2Model(ordinal);
//    
//    if( r == null )
//    {
//      None;
//    }
//    else
//    {
//      new Some(r);
//    }
//    
//    
//    
//  }
  
  
  def getQualifiedFieldName(fieldOrArrayOrdinal : LocationInClass,stackTraceGraph : StackTraceGraph ) =
  {
  fieldOrArrayOrdinal.getName(this,stackTraceGraph);
     
   
  }
  
  
  

  
  
}