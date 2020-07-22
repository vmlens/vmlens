package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.process._
import com.anarsoft.race.detection.process.workflow.SingleStep

class StepCheckPotentialDeadlocks extends   SingleStep[ContextDetectDeadlocks] {
  
    
   def execute( context : ContextDetectDeadlocks)
  {
       
     val list = context.potentialDeadlockWithParentMonitorIdsList;
   
     if(list != null)
     {
        SortArrayList.sort(    list , new ComparatorPotentialDeadlocks());
    
   var currentAlgo = new  CheckPotentialDeadlocksAlgo(-1,-1);
   val iter = list.iterator();
     
    while( iter.hasNext() )
    {
       val current = iter.next();
       
       if(  currentAlgo.higherMonitor !=  current.potentialDeadlockAsKey.higherMonitorId ||  currentAlgo.lowerMonitor !=  current.potentialDeadlockAsKey.lowerMonitorId   )
       {
             currentAlgo.addDeadlocks2Set(context.deadlocks);
             currentAlgo = new  CheckPotentialDeadlocksAlgo( current.potentialDeadlockAsKey.higherMonitorId  ,  current.potentialDeadlockAsKey.lowerMonitorId  )
       }
      
       
       currentAlgo.processPotentailDeadlock(current);
       
       
       
       
     }
    
  currentAlgo.addDeadlocks2Set(context.deadlocks);
     }
     
    
    
    
    
     
     
     
     
  }
  
  
}