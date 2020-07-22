package com.anarsoft.race.detection.process.result


import com.anarsoft.race.detection.model.description._;
import scala.collection.mutable.HashMap;
import  com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap
import java.io._;
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.aggregate.create.BuildAggregateOrdinalIn


class StepReadDescription(val eventDir : String)  extends SingleStep[ContextReadDescription]  {
  
  
  def execute(context : ContextReadDescription)
  {
           
    context.arrayAndFieldOrdinalMap = new LocationInClassOrdinalBuilder(context.fieldIdMap , 
        new BuildAggregateOrdinalIn(context.methodAggregateId4Array.sameAggregateId, context.methodAggregateId4Array.currentAggregateId)  ,  context.methodAggregateId4Array.id4Aggregate2AggregateId).create();
      
      
      //context.locationInClassOrdinalBuilder.create();
    
    
      context.ownerId2Name = new HashMap[Int,String]
        
      
       
        val descriptionRepositoryBuilder = new DescriptionRepositoryBuilderMethondAndFields(context.stackTraceGraph.methodOrdinal2Model , context.methodId2Ordinal ,context.arrayAndFieldOrdinalMap,  context.ownerId2Name);
       
      context.arrayAndFieldOrdinalMap.setFieldIds4TemplateClasses();
      
       
       val dir = new File(eventDir);
       
       for( file <- dir.listFiles() )
       {
         if( file.getName.contains("description_")) 
         {
           descriptionRepositoryBuilder.buildFromFile(file);
         }
       }
       
       
       
       
       
     context.methodOrdinalAndFieldOrdinal2Operation = descriptionRepositoryBuilder.methodOrdinalAndFieldOrdinal2Operation
    
    
  }
  
  def desc = "";
  
}