package com.anarsoft.race.detection.process.result

import com.anarsoft.race.detection.model.description._;
import scala.collection.mutable.HashMap;
import  com.anarsoft.race.detection.model.field.ArrayAndFieldOrdinalMap
import java.io._;
import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.aggregate.create.BuildAggregateOrdinalIn


class StepReadDescriptionMethodOnly(val eventDir : String)  extends SingleStep[ContextReadDescriptionMethodOnly]  {
  
  
  def execute(context : ContextReadDescriptionMethodOnly)
  {
           
  
         context.ownerId2Name = new HashMap[Int,String]
       
        val descriptionRepositoryBuilder = new DescriptionRepositoryBuilderMethodOnly(context.stackTraceGraph.methodOrdinal2Model , context.methodId2Ordinal ,  context.ownerId2Name );
       
       
       val dir = new File(eventDir);
       
       for( file <- dir.listFiles() )
       {
         if( file.getName.contains("description_")) 
         {
           descriptionRepositoryBuilder.buildFromFile(file);
         }
       }
       
       

    
    
  }
  
  
}