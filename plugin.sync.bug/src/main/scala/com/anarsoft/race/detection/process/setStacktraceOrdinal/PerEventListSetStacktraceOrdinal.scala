package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process._;
import java.util.ArrayList;
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract



class PerEventListSetStacktraceOrdinal( val getCurrentReadFieldsF : ContextSetStacktraceOrdinal => ArrayList[_ <: EventSetStacktraceOrdinal]   )  
extends PerEventListAbstract[EventSetStacktraceOrdinal,ContextSetStacktraceOrdinal] {
  
  
  def getCurrentReadFields ( context : ContextSetStacktraceOrdinal ) = getCurrentReadFieldsF(context);
  val comparator = new  Comparator4SetStacktraceOrdinal[EventSetStacktraceOrdinal]();
  
  def processEventList(  list : ArrayList[_ <: EventSetStacktraceOrdinal] ,context : ContextSetStacktraceOrdinal  )
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
        
         
         
           threadOrdinal = context.threadNames.getThreadOrdinal(current.threadId )    //.get( current.threadId ).get;
         currentThreadId =  current.threadId;
         methodFlowPerThread = context.methodFlow.getMethodFlowPerThread( currentThreadId );
       }
   
    
     
       
       
       current.setStackTraceOrdinal( methodFlowPerThread , context.stackTraceTree , context.threadOrdinalAndStackTraceSet ,threadOrdinal );
       
       
        
        
     }
  }
  
  
  
}



