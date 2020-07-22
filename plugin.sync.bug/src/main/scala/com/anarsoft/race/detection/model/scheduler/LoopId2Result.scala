package com.anarsoft.race.detection.model.scheduler

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.interleave._
import scala.collection.mutable.HashMap
import scala.collection.mutable.HashSet

class LoopId2Result(val id2Result :  HashMap[Int,LoopResult],val  loopId2Name : HashMap[Int,String]) {
 
   
     
  
  
  def createNameAndResult() =
  {
   
   val alreadyVisited = new HashSet[String] 
   val namesWithDuplicate =  new HashMap[String,Int] 
   
   for( elem <- id2Result )
   {
     val name =   loopId2Name.getOrElse(elem._1, "undefined");
     
     if( alreadyVisited.contains(name) )
     {
       namesWithDuplicate.put(name , 0);
     }
     
     alreadyVisited.add(name)
     
   }
   
    
    
   val  id2RealName = new HashMap[Int,String] 
   
    for( elem <- id2Result )
   {
     val name =   loopId2Name.getOrElse(elem._1, "undefined");
     
     namesWithDuplicate.get(name) match
     {
       
       case None =>
         {
           id2RealName.put( elem._1 , name );
         }
       
       case Some(x) =>
         {
            id2RealName.put( elem._1 , name + "_" + x );  
           
           
            namesWithDuplicate.put( name , x + 1 );
            
         }
         
       
     }
     
     
     
   }
   
   
 
    
    
    
    
  id2Result.map(  t   => {

    Tuple2(  id2RealName.getOrElse(t._1, "undefined")   ,t._2 )    }
  
  
  
  ).toSeq

  }
  
  
}