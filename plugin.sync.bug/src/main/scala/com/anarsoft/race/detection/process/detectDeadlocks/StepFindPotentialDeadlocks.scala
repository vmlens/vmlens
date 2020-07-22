package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.process._
import  com.anarsoft.race.detection.process.monitorRelation._
import com.anarsoft.race.detection.process.workflow.SingleStep


class StepFindPotentialDeadlocks extends  SingleStep[ContextDetectDeadlocks]  {
  
   def execute( context : ContextDetectDeadlocks)
  {
     
   val potentialDeadlockMap = new ThreadId2PotentialDeadlock();  
     
   val list = context.monitorRelationList;
   
   if( list != null )
   {
      SortArrayList.sort(    list , new ComparatorMonitorRelation());
     
   
   var currentAlgo = new  FindPotentialDeadlocksAlgo(-1,-1);
   val iter = list.iterator();
     
    while( iter.hasNext() )
    {
       val current = iter.next();
       
       if(  currentAlgo.higherMonitorId !=  current.higherMontorId ||  currentAlgo.lowerMonitorId !=  current.lowerMonitorId   )
       {
             currentAlgo.addPotentialDeadlocks2List(potentialDeadlockMap);
             currentAlgo = new  FindPotentialDeadlocksAlgo(current.higherMontorId ,  current.lowerMonitorId )
       }
      
       
       currentAlgo.processRelation(current);
       
       
       
       
     }
    
    currentAlgo.addPotentialDeadlocks2List(potentialDeadlockMap);
    
   }
   
  
    
    context.potentialDeadlockMap = potentialDeadlockMap;
     
     
   
  }
  
}