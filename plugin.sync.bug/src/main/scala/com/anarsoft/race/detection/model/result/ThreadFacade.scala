package com.anarsoft.race.detection.model.result

import scala.collection.mutable.HashMap

class ThreadFacade( val id2ThreadName : HashMap[Long,String] ,val ordinal2ThreadId : Array[Long] ) {
  
  
   def getThreadName( id : Long ) = {
    
    
    
    id2ThreadName.get(id) match
    {
      
      case None => "";
      
      case Some(x) =>
        {
          x;
        }
    }
  }
   
   
   
 def getThreadModelForOrdinal( ordinal : Int ) =
 {
     val id = ordinal2ThreadId(ordinal);
     
     id2ThreadName.get(id) match
     {
       
       case None =>
         {
           new ThreadModel(  "" , id )
         }
       case Some(x) =>
         {
            new ThreadModel(  x , id )
         }
       
     }
     
   
    
  
 }
  
  
}