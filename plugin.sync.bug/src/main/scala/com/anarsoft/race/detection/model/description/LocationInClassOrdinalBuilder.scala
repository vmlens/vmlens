package com.anarsoft.race.detection.model.description

import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model.field._;
import com.anarsoft.race.detection.model.description._;
import com.anarsoft.race.detection.model.result.FieldInClass
import com.anarsoft.race.detection.model.result._
import com.anarsoft.race.detection.process.aggregate.create._;
import com.anarsoft.race.detection.process.aggregate._;
import  com.anarsoft.race.detection.process.field.FieldIdMap



class LocationInClassOrdinalBuilder( val fieldIdMap : FieldIdMap,val arrayMethodOrdinalBuildAggregateOrdinalIn : BuildAggregateOrdinalIn, 
    val arrayMethodOrdinalAndPostion2AggregateId : HashMap[MethodOrdinalAndPosition,Int] )   {
  
  


  
  def create() =
  {
   
    // start array ordinal erzeugung
     val buildAggregateOrdinal = new BuildAggregateOrdinalAbstract();
     val  currentOrdinalId = new MutableInt(0);
    
        
        
    
   val aggregateId2Ordinal =    buildAggregateOrdinal.createAggregateId2AggregateOrdinal(arrayMethodOrdinalBuildAggregateOrdinalIn, currentOrdinalId)    
    
   
   val arrayLocationOrdinal2ArrayModel = Array.ofDim[ArrayModel]( currentOrdinalId.value )
   
   
   
   
   for( elem <- arrayMethodOrdinalAndPostion2AggregateId )
   {
     val ordinal = aggregateId2Ordinal(  elem._2  );
     
     if(arrayLocationOrdinal2ArrayModel(ordinal) == null)
     {
         arrayLocationOrdinal2ArrayModel(ordinal) = new ArrayModel();
     }
    
     arrayLocationOrdinal2ArrayModel(ordinal).methodAndPosition.append( elem._1 );
         
     
   }
   
   
   
   // end array ordinal erzeugung
   
   
    
    val fieldOrdinal2FieldModelDesc =  Array.ofDim[FieldModel](fieldIdMap.currentFieldOrdinal)
    
    new ArrayAndFieldOrdinalMap(fieldIdMap.fieldId2Ordinal , fieldOrdinal2FieldModelDesc , aggregateId2Ordinal , arrayLocationOrdinal2ArrayModel );
  }
  
  
  
}