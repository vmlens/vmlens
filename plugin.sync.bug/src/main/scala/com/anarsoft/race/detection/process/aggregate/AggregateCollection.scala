package com.anarsoft.race.detection.process.aggregate

import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

abstract class AggregateCollection[ID,-EVENT]  {
   
   val id4Aggregate2AggregateId = new HashMap[ID,Int]();
   var sameAggregateId = new HashSet[SameAggregateId];
   var currentAggregateId = 0;
 
   
  protected def onNewAggregate( id : ID , event : EVENT  );
   
  
  def createAggregateId(id : ID, event : EVENT) =
  {
    
  
    
   
   id4Aggregate2AggregateId.get(id) match
   {
     case None =>
       {
         val x = currentAggregateId;
         currentAggregateId = currentAggregateId + 1;
         
       
      
         
         id4Aggregate2AggregateId.put(id ,x);
         onNewAggregate(id,event);
         
         
         x;
         
         
       }
     
     case Some(x) =>
       {
         x;
       }
     
   }
    
    
  }
  
  
  
  
  
  
  
  
  
  def addExistent(id : ID,existent : Int )
  {
     
    
   
   id4Aggregate2AggregateId.get(id) match
   {
     case None =>
       {
        
         
         
         id4Aggregate2AggregateId.put(id , existent );
         
         
         
       }
     
     case Some(x) =>
       {
     
         
          
         
          if(  x  != existent  )
          {
            sameAggregateId.add(SameAggregateId(x,existent ));
          }
         
         
         
         
       }
     
   }
    
    
    
  }
  
  
  
  
  
  
  
  
  
  
  
  
}