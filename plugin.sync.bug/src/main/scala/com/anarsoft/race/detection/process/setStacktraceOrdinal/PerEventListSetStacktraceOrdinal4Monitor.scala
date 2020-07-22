package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import java.util.ArrayList
import com.anarsoft.race.detection.process.method._;

class PerEventListSetStacktraceOrdinal4Monitor extends PerEventListAbstract[MonitorEvent,ContextSetStacktraceOrdinal]  {
  
  def getCurrentReadFields ( context : ContextSetStacktraceOrdinal ) = context.monitorEventList;
  def comparator  = new  Comparator4SetStacktraceOrdinal();
  
  def processEventList(  list : ArrayList[_ <: MonitorEvent] ,context : ContextSetStacktraceOrdinal  )
  {
      var currentThreadId = -1L;
      var threadOrdinal = -1;
     
     var methodFlowPerThread : MethodFlowPerThread = null;
     val iter = list.iterator();
     
     while( iter.hasNext() )
     {
       val current = iter.next();
      
       
       if(currentThreadId != current.threadId)
       {
        
         
         
         threadOrdinal = context.threadNames.getThreadOrdinal(current.threadId );
         currentThreadId =  current.threadId;
         methodFlowPerThread = context.methodFlow.getMethodFlowPerThread( currentThreadId );
       }
   
current.setStackTraceOrdinal( methodFlowPerThread , context.stackTraceTree , context.threadOrdinalAndStackTraceSet ,threadOrdinal ) 
       
     
   
       
       
       
        
        
     }
  }
  
    
  
}