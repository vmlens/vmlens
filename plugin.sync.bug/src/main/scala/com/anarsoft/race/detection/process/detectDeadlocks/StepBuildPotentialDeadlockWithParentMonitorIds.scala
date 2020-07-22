package com.anarsoft.race.detection.process.detectDeadlocks

import com.anarsoft.race.detection.process._
import com.anarsoft.race.detection.process.workflow.SingleStep
import  com.anarsoft.race.detection.process.monitorRelation.ComparatorEvent4MonitorRelation

class StepBuildPotentialDeadlockWithParentMonitorIds extends   SingleStep[ContextDetectDeadlocks]  {
  
   def execute( context : ContextDetectDeadlocks)
  {
    
    context.resetContextDetectDeadlocks();
    
     val currentReadFields = context.monitorEventList;
    
     if(currentReadFields != null)
     {
          SortArrayList.sort(    currentReadFields , new ComparatorEvent4MonitorRelation());
 
    var currentThreadId = -1L;
    var currentPerThreadIdAlgo : Option[BuildPotentialDeadlockWithParentMonitorIdsAlgo] = None;
    
    
    
   
    val iter = currentReadFields.iterator();
     
     while( iter.hasNext() )
     {
       val current = iter.next();
       
       if( current.threadId != currentThreadId )
       {
          currentPerThreadIdAlgo.foreach( x =>    x.addResult2Context(  ) )

         currentPerThreadIdAlgo =  BuildPotentialDeadlockWithParentMonitorIdsAlgo(current,context); 
         currentThreadId =  current.threadId;
         
       } 
        currentPerThreadIdAlgo.foreach( x =>    x.processEvent(current) )
       
       
         
         
     }
     
      currentPerThreadIdAlgo.foreach( x =>    x.addResult2Context(  ) )
     }
     
     
  
     
     

     
  
  }
  
  
  
}