package com.anarsoft.race.detection.process.monitorRelation

import com.anarsoft.race.detection.process._
import java.util.ArrayList;
import scala.collection.mutable.Stack
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.process.workflow.SingleStep

class StepCreateMonitorRelation(  ) extends   SingleStep[ContextCreateMonitorRelation] {
  
  
  
 
  
  
  def execute( context : ContextCreateMonitorRelation)
  {
    
    context.newRoundCreateMonitorRelation();
    
     val currentReadFields = context.monitorEventList;
    
     if(currentReadFields != null)
     {
         
     SortArrayList.sort(    currentReadFields , new ComparatorEvent4MonitorRelation());
 
    var currentThreadId = -1L;
    var currentPerThreadIdAlgo : CreateMonitorRelationAlgo = null;
    
    
    
   
    val iter = currentReadFields.iterator();
     
     while( iter.hasNext() )
     {
       val current = iter.next();
       
       if( current.threadId != currentThreadId )
       {
         if(  currentPerThreadIdAlgo != null )
         {
           currentPerThreadIdAlgo.addResult2Context(  );
         }
         
         currentPerThreadIdAlgo =  CreateMonitorRelationAlgo(current,context); 
         currentThreadId =  current.threadId;
         
       } 
       
       
       currentPerThreadIdAlgo.processEvent(current);
         
         
     }
     
      if(  currentPerThreadIdAlgo != null )
         {
           currentPerThreadIdAlgo.addResult2Context(  );
         }
     
     
     }
   

     
  
  }
  
}