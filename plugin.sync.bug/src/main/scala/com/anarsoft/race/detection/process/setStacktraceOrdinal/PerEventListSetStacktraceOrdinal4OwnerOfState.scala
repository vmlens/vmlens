package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import com.anarsoft.race.detection.process.perEventList.StepProcessSingleEventList
import com.anarsoft.race.detection.process.monitor._
import java.util.ArrayList
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.model.result._

class PerEventListSetStacktraceOrdinal4OwnerOfState(val getCurrentReadFieldsF : ContextSetStacktraceOrdinal4OwnerOfState => ArrayList[_ <: EventSetStacktraceOrdinal])  extends PerEventListAbstract[EventSetStacktraceOrdinal,ContextSetStacktraceOrdinal4OwnerOfState]  {
  
  def getCurrentReadFields ( context : ContextSetStacktraceOrdinal4OwnerOfState )  = getCurrentReadFieldsF(context);

  
  
  def comparator  = new  Comparator4SetStacktraceOrdinal();
  
  def processEventList(  list : ArrayList[_ <: EventSetStacktraceOrdinal] ,context : ContextSetStacktraceOrdinal4OwnerOfState  )
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
        
         
         
         threadOrdinal = context.threadNames.threadId2ThreadOrdinal.get( current.threadId ).get;
         currentThreadId =  current.threadId;
         methodFlowPerThread = context.methodFlow.getMethodFlowPerThread( currentThreadId );
       }
   
       
     
       
        current.setStackTraceOrdinal( methodFlowPerThread , context.stackTraceTree , context.threadOrdinalAndStackTraceSet ,threadOrdinal )
        
        
//        match
//       {
//         
//         case None =>
//           {
//            
//             
//             
//           }
//         
//        
//         case Some(id) =>
//           {
//             
//             
//        
//               current.isThreadSafe = true
//             
//             
//        //     context.calculateSchedulingInfoPerRun.add(current,id);
//             
////            val list =  context.parallizeId2EventList.getOrElseUpdate( id  ,  EventAndBlockStatementList() )
////            list.add(current);  
////            
//             
//             
//           }
//           
           
         
         
     //  }
       
      
        
        
     }
  }
  
    
  
}


