package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.graph._;
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap;
import com.anarsoft.race.detection.process._;
import com.anarsoft.race.detection.model.method.StackTraceElement2Id;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.process.field.FieldIdMap
import com.anarsoft.race.detection.process.aggregate.AggregateCollectionWithoutAggregateInfo;
import  com.anarsoft.race.detection.model.description.MethodOrdinalAndFieldOrdinal


trait ContextReadDescription {
  
   def stackTraceGraph : StackTraceGraph;
   def methodId2Ordinal :  ModelKey2OrdinalMap[Int];
 

    var   ownerId2Name : HashMap[Int,String] = null; 
   
   var arrayAndFieldOrdinalMap : ArrayAndFieldOrdinalMap = null;
 
   var methodOrdinalAndFieldOrdinal2Operation : HashMap[MethodOrdinalAndFieldOrdinal,Int] = null;
   
   
//  def locationInClassOrdinalBuilder : LocationInClassOrdinalBuilder
  def   stackTraceElement2Id : StackTraceElement2Id; 
  def fieldIdMap : FieldIdMap;
  def methodAggregateId4Array : AggregateCollectionWithoutAggregateInfo[MethodOrdinalAndPosition,Any];
  
 
  
  
   // var packageGraph : PackageGraph = null;
}