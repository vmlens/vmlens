package com.anarsoft.race.detection.process.partialOrder

import scala.collection.mutable.HashMap


import com.anarsoft.race.detection.model._
import scala.collection.mutable.ArrayBuffer
import com.typesafe.scalalogging.Logger

class PartialOrderPerSlidingWindowId(val higherLowerThreadId2SyncPointMap: HashMap[HigherLowerThreadId, ThreadMapEntryFixed]) {

   val logger = Logger(classOf[PartialOrderPerSlidingWindowId])
  
   var listBasedMap : Option[ListBasedMap4Equals] = None;
    
   
   def asListBasedMap() =
   {
     listBasedMap match
     {
       case None =>
         {
           val buffer = new ArrayBuffer[ListBasedMapEntry4Equals]
           
           for( elem <- higherLowerThreadId2SyncPointMap )
           {
             buffer.append(new ListBasedMapEntry4Equals( elem._1.higherId ,  elem._1.lowerId , elem._2  ));
             buffer.append(new ListBasedMapEntry4Equals(  elem._1.lowerId , elem._1.higherId   , elem._2  ));
           }
           
           
           
           val sorted = buffer.sortWith( ( left , right ) =>  {  left.key < right.key   }    )
           
   
           
           val map = ListBasedMap4Equals( sorted );
           
           
           listBasedMap = Some(map);
           
           map;
         }
       
       case Some(x) =>
         {
           x;
         }
       
       
     }
   }
   
   
   
//  def isLeftBeforeRightTransetive(left: WithStatementPosition, right: WithStatementPosition) =
//    {
//     
//     logger.debug("isLeftBeforeRightTransetive");
//     
//      val elementList = new ArrayBuffer[Tuple2[HigherLowerThreadId, ThreadMapEntry]]
//
//      for (elem <- higherLowerThreadId2SyncPointMap) {
//        if (elem._1.higherId == right.threadId || elem._1.lowerId == right.threadId) {
//          elementList.append(elem)
//        }
//      }
//
//      var isBefore = false;
//      var iter = elementList.iterator
//
//      while (iter.hasNext && !isBefore) {
//        val current = iter.next()
//        current._2.getHighestLeft(current._1, right) match {
//          case None =>
//            {
//
//            }
//          case Some(highestLeft) =>
//            {
//              
//                logger.debug("isLeftBeforeRightTransetive " + highestLeft + " " + right);
//              
//              if (isLeftBeforeRight(left, highestLeft)) {
//                isBefore = true;
//              }
//            }
//        }
//      }
//      isBefore;
//    
//  
//    
//    }
  
   def isLeftBeforeRightTransetive(left: WithStatementPosition, right: WithStatementPosition) =
   {
     new LeftBeforeRightTransetiveAlgo(this).isLeftBeforeRightTransetive(left, right);
   }
  
  

  def isLeftBeforeRight(left: WithStatementPosition, right: WithStatementPosition) =
    {
      val key = HigherLowerThreadId(left.threadId, right.threadId);
      higherLowerThreadId2SyncPointMap.get(key) match {

        case None =>
          {
            false;
          }

        case Some(x) =>
          {
            logger.debug("" + right.threadId);
            x.isLeftBeforeRight(key, left, right.programCounter);

          }

      }

    }

}