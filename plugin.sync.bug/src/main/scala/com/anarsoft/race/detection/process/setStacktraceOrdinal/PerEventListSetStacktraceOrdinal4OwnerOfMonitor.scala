package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.perEventList.PerEventListAbstract
import com.anarsoft.race.detection.process.perEventList.StepProcessSingleEventList
import com.anarsoft.race.detection.process.monitor._
import java.util.ArrayList
import com.anarsoft.race.detection.process.method._;
import com.anarsoft.race.detection.model.result._


class PerEventListSetStacktraceOrdinal4OwnerOfMonitor extends PerEventListAbstract[MonitorEvent,ContextSetStacktraceOrdinal4OwnerOfMonitor]  {
  
  def getCurrentReadFields ( context : ContextSetStacktraceOrdinal4OwnerOfMonitor ) = context.monitorEventList;
  def comparator  = new  Comparator4SetStacktraceOrdinal();
  
  def processEventList(  list : ArrayList[_ <: MonitorEvent] ,context : ContextSetStacktraceOrdinal4OwnerOfMonitor  )
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
         // match
//       {
//         
//         case None =>
//           {
//             if( current.isInstanceOf[MonitorEnterEvent] )
//             {
//               current.asInstanceOf[MonitorEnterEvent].isThreadSafe = false
//             }
//           }
//         
//        
//         case Some(id) =>
//           {
//             
//             
//             
//              if( current.isInstanceOf[MonitorEnterEvent] )
//             {
//                current.asInstanceOf[MonitorEnterEvent].isThreadSafe = true
//             }
//             
//        //     context.calculateSchedulingInfoPerRun.add(current,id);
//             
////            val list =  context.parallizeId2EventList.getOrElseUpdate( id  ,  EventAndBlockStatementList() )
////            list.add(current);  
////            
//             
//             
//           }
           
           
         
         
   //    }
       
        if( current.isMonitorEnter() )
        {
          context.stackTraceOrdinalWithMonitor.add(  current.stackTraceOrdinal  );
        }
       
        
        
     }
  }
  
    
  
}

object PerEventListSetStacktraceOrdinal4OwnerOfMonitor
{
  
  def apply() =
  {
    new StepProcessSingleEventList[ContextSetStacktraceOrdinal4OwnerOfMonitor](new PerEventListSetStacktraceOrdinal4OwnerOfMonitor() , classOf[ContextSetStacktraceOrdinal4OwnerOfMonitor] )
  }
  
  
  
}



