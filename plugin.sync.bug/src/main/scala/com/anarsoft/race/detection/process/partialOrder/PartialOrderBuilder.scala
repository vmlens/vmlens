package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field._
import scala.collection.mutable.HashMap;



class PartialOrderBuilder {
  
  

  
  val higherLowerThreadId2SyncPointMap = new HashMap[HigherLowerThreadId,ThreadMapEntry]
  
  
  
  
   

    def leftComesBeforeRight(left : WithStatementPosition, right : WithStatementPosition)
   { 
     val key = HigherLowerThreadId(left.threadId , right.threadId); 
     val entry=  higherLowerThreadId2SyncPointMap.getOrElseUpdate(key, new ThreadMapEntry())
    
     entry.leftComesBeforeRight(key , left.programCounter , left.threadId, right.programCounter);
        
     }
  
  
  
  
  
    private[partialOrder] def removeNonContinuousSyncPoints()
    {
      
      for( e <-  higherLowerThreadId2SyncPointMap )
      {
        e._2.removeNonContinuousSyncPoints();
      }
    }

  def create() =
  {
    
    val temp = new HashMap[HigherLowerThreadId,ThreadMapEntryFixed]
    val keys = higherLowerThreadId2SyncPointMap.keySet.iterator
    
    while( keys.hasNext )
    {
      val current = keys.next();
      temp.put( current , higherLowerThreadId2SyncPointMap.get(current).get.toFixed() );
    }
    new PartialOrderPerSlidingWindowId(temp);
  }

}