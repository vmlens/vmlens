package com.anarsoft.race.detection.process.syncAction

import scala.collection.mutable.HashMap;
import  com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric
import  com.anarsoft.race.detection.process.partialOrder.EventWithOrder
import com.anarsoft.race.detection.model.WithStatementPosition
import java.util.ArrayList
import com.anarsoft.race.detection.model.parallize._;
import scala.reflect.ClassTag



// SyncPointGeneric[_,_]

class PrevoiusStartHappensBeforeMap[SYNC_POINT <: WithStatementPosition with Object : ClassTag]( val threadId2CurrentIndex :  HashMap[Long,CurrentIndex] , var prevoiusStarts : Array[SYNC_POINT]   ) {
 
  
  var currentSize = 0;
  
  def clear()
  {
    for( i <- 0 until currentSize )
    {
      threadId2CurrentIndex.get( prevoiusStarts(i).threadId ).get.index = None;
    }
    
    currentSize = 0
    
    
    
  }
  
  def put( point :  SYNC_POINT )
  {
    
    val fromMap  = threadId2CurrentIndex.get(point.threadId );
    
    
    val elem = 
    fromMap match
    {
      case None =>
        {
          val x=  new CurrentIndex();
          
          threadId2CurrentIndex.put(point.threadId ,  x )
          val dest = Array.ofDim[SYNC_POINT]( threadId2CurrentIndex.size )
          Array.copy(prevoiusStarts, 0, dest, 0, prevoiusStarts.size)
          
          prevoiusStarts = dest;
          
          x;
        }
      
        
      case Some(x) => x;   
      
      
    }
    
    
    
    elem.index match
    {
      
      case None =>
        {
          prevoiusStarts(currentSize) = point;
           elem.index = Some(currentSize);
          currentSize = currentSize + 1; 
           
        }
      
        
      case Some(x) =>
        {
          prevoiusStarts(x) = point;
        }
      
    }
    
    
    
  }
  
  
  def foreach( f : SYNC_POINT => Unit )
  {
      for( i <- 0 until currentSize )
    {
     f( prevoiusStarts(i));
    }
  }
  
  
}


object PrevoiusStartHappensBeforeMap
{
  def create4SyncPointGeneric(id2ThreadName : HashMap[Long,String]) =
  {
    val  threadId2CurrentIndex = new  HashMap[Long,CurrentIndex]()
    
    for( elem <- id2ThreadName )
    {
      threadId2CurrentIndex.put( elem._1 , new CurrentIndex() );
    }
    
    
    new PrevoiusStartHappensBeforeMap[SyncPointGeneric[_]](threadId2CurrentIndex , Array.ofDim( threadId2CurrentIndex.size ));
    
  }
  
  
  def create4EventWithOrder(id2ThreadName : HashMap[Long,String]) =
  {
    val  threadId2CurrentIndex = new  HashMap[Long,CurrentIndex]()
    
    for( elem <- id2ThreadName )
    {
      threadId2CurrentIndex.put( elem._1 , new CurrentIndex() );
    }
    
    
    new PrevoiusStartHappensBeforeMap[EventWithOrder[_]](threadId2CurrentIndex , Array.ofDim( threadId2CurrentIndex.size ));
    
  }
  
  
//  def create4StatementBlocks(threadId2StatementBlockList : HashMap[Long,ArrayList[StatementBlock]]) =
//  {
//     val  threadId2CurrentIndex = new  HashMap[Long,CurrentIndex]()
//    
//    for( elem <- threadId2StatementBlockList )
//    {
//      threadId2CurrentIndex.put( elem._1 , new CurrentIndex() );
//    }
//    
//    
//    new PrevoiusStartHappensBeforeMap[SyncStatementBlock](threadId2CurrentIndex , Array.ofDim( threadId2CurrentIndex.size ));
//    
//  }
  
  
  
  
  
}


class CurrentIndex
{
  
  var index : Option[Int] = None;
  
  
}