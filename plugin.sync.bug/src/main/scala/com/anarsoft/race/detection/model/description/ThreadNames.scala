package com.anarsoft.race.detection.model.description

import scala.collection.mutable._;
import com.anarsoft.race.detection.model.result.ThreadFacade

class ThreadNames(  val id2ThreadName : HashMap[Long,String] ,  val mappedThreadId2ThreadId : HashMap[Byte,Long], 
    val shortThreadId2ThreadId : HashMap[Short,Long],val threadId2ThreadOrdinal : HashMap[Long,Int], var maxThreadOrdinal : Int) {
  
  
 
  
   def getThreadOrdinal(threadId : Long) =
   {
      threadId2ThreadOrdinal.get( threadId ) match
      {
        case Some(x) =>
          {
            x;
          }
        
        case None =>
          {
            val temp = maxThreadOrdinal;
            
            threadId2ThreadOrdinal.put( threadId , temp  );
            
            id2ThreadName.get(threadId) match
            {
              case None =>
                {
                  println("no ordinal and name for " + threadId);
                  id2ThreadName.put(threadId , "unknown " + threadId);
                }
              
              case Some(name) =>
                {
                  println("no ordinal for " + name + " " + threadId)
                }
              
            }
            
            maxThreadOrdinal = maxThreadOrdinal + 1;
            
            temp;
          }
        
      }
     
     
   }
  
   
   
  
  
   def getThreadSize()  : Int = id2ThreadName.size;
  
  
  
  def foreachThread( f : String => Unit )
  {
    id2ThreadName.values.foreach(f);
    
    
  }
  
  
  def createThreadFacade() =
  {
     val ordinal2ThreadId = Array.ofDim[Long](  threadId2ThreadOrdinal.size) 
     
     
     for( elem <-  threadId2ThreadOrdinal )
     {
       ordinal2ThreadId( elem._2  ) = elem._1;
     }
     
     
       new ThreadFacade(   id2ThreadName ,ordinal2ThreadId  ) 
     
  }
 
  
  
  

}