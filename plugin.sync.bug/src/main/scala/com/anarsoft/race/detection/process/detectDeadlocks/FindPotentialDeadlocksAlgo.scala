package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.process.monitorRelation.MonitorRelation
import scala.collection.mutable.HashSet



class FindPotentialDeadlocksAlgo( val higherMonitorId : Int, val lowerMonitorId : Int) {
  
  val parentThreadIdSet = new HashSet [Long];
  val childThreadIdSet  = new HashSet[Long]
  
  
  
  def processRelation(monitorRelation : MonitorRelation)
  {
    if( monitorRelation.higherMonitorIsChild )
    {
      childThreadIdSet.add(monitorRelation.threadId);
    }
    else
    {
      parentThreadIdSet.add(monitorRelation.threadId);
    }
  }
  
  
  
  def addPotentialDeadlocks2List(threadId2PotentialDeadlock :ThreadId2PotentialDeadlock)
  {
    if( parentThreadIdSet.size > 0 &&  childThreadIdSet.size > 0 )
    {
      
      for( parent <- parentThreadIdSet )
      {
        for( child <- childThreadIdSet)
        {
          
           if( parent != child  )
           {
             threadId2PotentialDeadlock.add(  parent , higherMonitorId ,  lowerMonitorId );
             threadId2PotentialDeadlock.add(  child , lowerMonitorId ,  higherMonitorId );  
           } 
          
          
          
        }
       
      }
      
      
      
    }
  }
  
  
  
}