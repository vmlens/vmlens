package com.vmlens.api.internal.reports.element

import scala.collection.mutable.HashMap


class ContextLastRun {
  
  val threadId2ThreadCount = new HashMap[Long,Int]
  var threadId2Style : Option[HashMap[Long,String]] = None; 
  
  def addThreadId(id : Long)
  {
    val count = threadId2ThreadCount.getOrElseUpdate( id , 0);
    threadId2ThreadCount.put(  id , count + 1 );
    
    
    
  }
  
  val VERY_DARK = "c2c6cb";
  val DARK = "d8dbdd";
  val BRIGHT = "eff0f1" 
  
  
  
  /*
   * ganz dunkel     
   *                 d8dbdd
   * dunkel      
   * heller      
   * ganz hell   
   * 
   */
  
  
  def buildThreadId2Style() =
  {
    
    val sorted = threadId2ThreadCount.toSeq.sortBy(  ( touple  ) =>  -1 * touple._2   );
    
    
    val colorList = List( None ,  Some(VERY_DARK) , Some(BRIGHT)    ,   Some(DARK)   )
    
    var index = 0;
       val newMap = new HashMap[Long,String]();
    
    for(elem <- sorted)
    {
      
      colorList(index) match
      {
        case None =>
          {
            
          }
        
        case Some(x) =>
          {
              newMap.put( elem._1 , """ style="background-color: #""" + x + "\""  );
          }
        
      }
      
      index = index + 1;
      
      if(index == colorList.length )
      {
        index = 0;
      }
      
      
    }
    
    
    
    newMap;
  }
  
  
  def getTrStyle(threadId : Long) =
  {
    val map = 
    threadId2Style match
    {
      
      case None =>
        {
       
          val newMap = buildThreadId2Style();
          
          threadId2Style = Some(newMap);
          
          newMap;
          
        }
      
      case Some(x) =>
        {
          x;
        }
      
      
      
    }
    
    
    map.get( threadId  );
    
    
  }
  
  
  
}

object ContextLastRun
{
   def main(args : Array[String])
   {
     val c = new ContextLastRun();
     
     c.addThreadId(1L)
     c.addThreadId(1L)
     c.addThreadId(1L)
     c.addThreadId(1L)
     
     
     c.addThreadId(2L)
     c.addThreadId(2L)
     c.addThreadId(2L)
     
       
     c.addThreadId(3L)
        
       
     c.addThreadId(4L)
      
     c.addThreadId(5L)
     c.addThreadId(6L)
     c.addThreadId(7L)
     
     
     println(  c.getTrStyle(1L) );
     println(  c.getTrStyle(2L) );
     println(  c.getTrStyle(7L) );
     println(  c.getTrStyle(3L) );
   }
  
  
  
  
}



