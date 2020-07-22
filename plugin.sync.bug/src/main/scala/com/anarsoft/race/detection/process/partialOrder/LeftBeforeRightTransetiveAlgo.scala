package com.anarsoft.race.detection.process.partialOrder

import scala.collection.mutable.HashMap
import com.anarsoft.race.detection.model._
import scala.collection.mutable.ArrayBuffer


class LeftBeforeRightTransetiveAlgo(val  partialOrder : PartialOrderPerSlidingWindowId) {
  
   val threadId2HighestOrder = new HashMap[Long,Int]
  
  
  
    def isLeftBeforeRightTransetive(left: WithStatementPosition, right: WithStatementPosition) : Boolean =
    {  
     
     
      val maxPos = threadId2HighestOrder.getOrElse(right.threadId, -1);
     
      if( maxPos >= right.programCounter )
      {
        false;
      }
      else if( left.threadId == right.threadId )
      {
        false;
      }
      else
      {
        threadId2HighestOrder.put(right.threadId, right.programCounter)
      
    
     
      val elementList = new ArrayBuffer[ListBasedMapEntry4Equals]

     
        
        
        partialOrder.asListBasedMap().onElements(right.threadId,   x => {
      
          elementList.append(x)   } );    
        
        
//      for (elem <- partialOrder.asArrayBuffer()) {
//        if (elem._1.higherId == right.threadId || elem._1.lowerId == right.threadId) {
//          elementList.append(elem)
//        }
//      }

      var isBefore = false;
      var iter = elementList.iterator
    
      val checkAgain = new ArrayBuffer[WithStatementPosition];
      
      
      while (iter.hasNext && !isBefore) {
        val current = iter.next()
        current.threadMapEntry.getHighestLeft(current.value, right) match {
          case None =>
            {

            }
          case Some(highestLeft) =>
            {
              if (partialOrder.isLeftBeforeRight(left, highestLeft)) {
                isBefore = true;
              }
              else
              {
                checkAgain.append( highestLeft );
              }
              
              
            }
        }
      }
      
      if( ! isBefore )
      {
        var iter = checkAgain.iterator
          while (iter.hasNext && !isBefore) {
             val highestLeft = iter.next()
             if (isLeftBeforeRightTransetive(left, highestLeft)) {
                isBefore = true;  
               
              }
            
          }
        
        
        
      }
      
      
      
      isBefore;
      
      }
      
    }
  
  
}