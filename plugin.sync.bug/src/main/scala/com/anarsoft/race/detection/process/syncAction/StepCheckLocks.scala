package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.workflow.SingleStep
import com.anarsoft.race.detection.process.SortArrayList
import java.util.ArrayList

class StepCheckLocks extends SingleStep[ContextProcessSyncAction] {
  
 
  
  
  def execute(context : ContextProcessSyncAction)
  {
     val exclusiveLocks= new ArrayList[SyncActionLock]
    
    val iter = exclusiveLocks.iterator();
    while( iter.hasNext() )
    {
      val current = iter.next();
      
      if( ! current.isShared )
      {
        exclusiveLocks.add(current);
      }
    }
    
    
    SortArrayList.sort(exclusiveLocks, new SortBasedOnOrder[Int,SyncActionLock]());
    
    var inLock : Option[SyncActionLock] = None;
    val sortedIter = exclusiveLocks.iterator();
    
    while(sortedIter.hasNext())
    {
      val current = iter.next();
      
      if( current.isLockEnter() )
      {
        inLock match
        {
          case None =>
            {
              
            }
          
          case Some(x) =>
            {
              if( x.monitorId == current.monitorId && x.threadId != current.threadId )
              {
                throw new RuntimeException("wrong lock order");
              }
              
              inLock = Some(current);
              
            }
          
        }
        
      }
      else
      {
        inLock = None;
      }
      
      
      
      
    }
  
  }
  
}