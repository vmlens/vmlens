package com.anarsoft.race.detection.process.partialOrder

import com.anarsoft.race.detection.model._
import com.anarsoft.race.detection.model.field._
import scala.collection.mutable.HashMap
import java.util.LinkedList


class PartialOrder extends PartialOrderAPI {
  
  
  
  
    
    
    

    
  
   
    val fifo : LinkedList[PartialOrderPerSlidingWindowId] = new LinkedList[PartialOrderPerSlidingWindowId]();
  
  
     def isLeftBeforeRight(left : WithStatementPosition , right : WithStatementPosition) =
    {
       
       var isBefore = false;
       val iter = fifo.iterator();
       
       while( iter.hasNext() && ! isBefore )
       {
         
         val current = iter.next();
         
         isBefore = current.isLeftBeforeRight(left, right);
         
         
       }
         
       
       isBefore;
       
       
    }
    
    
      
     def isLeftBeforeRightTransetive(left : WithStatementPosition , right : WithStatementPosition) =
    {
        var isBefore = false;
       val iter = fifo.iterator();
       
       while( iter.hasNext() && ! isBefore )
       {
         
         val current = iter.next();
         
         isBefore = current.isLeftBeforeRightTransetive(left, right);
         
         
       }
         
       
       isBefore;
       
    }
     
     
      
   def add(partialOrderBuilder : PartialOrderBuilder)
   {
    
     
     partialOrderBuilder.removeNonContinuousSyncPoints();
     
     fifo.addLast( partialOrderBuilder.create() );
    
    
    /**
     * 
     * ich lese einen block vorher und behalte den vorherigen Block, 
     * so das die events in der mitte liegen daher 3 + 1 da ich volatile 
     * und monitore vorher prozessiere
     * 
     */
    
    if( fifo.size() > 4 )
    {
      fifo.removeFirst();
    }
   }
      
         
}         
       
// new HashMap[Long,ThreadSyncPointMap]()

         
object PartialOrder
{
  def apply() =
  {
    new PartialOrder();
  }
  
  
  
}