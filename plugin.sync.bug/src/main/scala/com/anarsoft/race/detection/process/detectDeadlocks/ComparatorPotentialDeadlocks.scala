package com.anarsoft.race.detection.process.detectDeadlocks

import java.util.Comparator



class ComparatorPotentialDeadlocks extends Comparator[PotentialDeadlock] {
  
   def	compare(o1 :  PotentialDeadlock,  o2 : PotentialDeadlock ) =
    {
       
          
        
        if( o1.potentialDeadlockAsKey.higherMonitorId != o2.potentialDeadlockAsKey.higherMonitorId )
          {
             java.lang.Integer.compare( o1.potentialDeadlockAsKey.higherMonitorId , o2.potentialDeadlockAsKey.higherMonitorId  )
          }
        else if( o1.potentialDeadlockAsKey.lowerMonitorId != o2.potentialDeadlockAsKey.lowerMonitorId )
          {
             java.lang.Integer.compare( o1.potentialDeadlockAsKey.lowerMonitorId , o2.potentialDeadlockAsKey.lowerMonitorId  )
          } 
         else if( o1.potentialDeadlockAsKey.higherMonitorIsChild != o2.potentialDeadlockAsKey.higherMonitorIsChild )
          {
             java.lang.Boolean.compare( o1.potentialDeadlockAsKey.higherMonitorIsChild , o2.potentialDeadlockAsKey.higherMonitorIsChild  )
          } 
         else
          {
             java.lang.Long.compare( o1.potentialDeadlockAsKey.threadId , o2.potentialDeadlockAsKey.threadId  )
          }
         
    }  
  
  
}