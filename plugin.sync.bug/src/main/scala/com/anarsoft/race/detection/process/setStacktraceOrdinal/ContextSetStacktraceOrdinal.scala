package com.anarsoft.race.detection.process.setStacktraceOrdinal

import com.anarsoft.race.detection.process.nonVolatileField.ContextNonVolatileFields
import com.anarsoft.race.detection.process.method._
import com.anarsoft.race.detection.process.volatileField.ContextVolatileField;
import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.syncAction.ContextProcessSyncAction
import org.roaringbitmap.RoaringBitmap;
import scala.collection.mutable.HashSet
import com.anarsoft.race.detection.model.description.ThreadNames;
import scala.collection.mutable.HashMap
import java.util.ArrayList
import  com.anarsoft.race.detection.process.scheduler.XMethodEvent
//import com.anarsoft.race.detection.process.parallize.EventAndBlockStatementList


trait ContextSetStacktraceOrdinal extends ContextVolatileField with ContextNonVolatileFields with ContextMonitor with ContextProcessSyncAction {
  
  
   //var parallizeId2EventList : HashMap[Int,EventAndBlockStatementList] = null;
    
    var atomicMethodEventList : ArrayList[XMethodEvent] = null;

  
    def initializeContextSetStacktraceOrdinal()
    {
      atomicMethodEventList = new ArrayList[XMethodEvent]();
    }
    
    
  
    def methodFlow : MethodFlow;
    def stackTraceTree : StackTraceTree;
   def threadNames  : ThreadNames;
   def threadOrdinalAndStackTraceSet  : HashSet[ThreadOrdinalAndStackTrace];
    
   
   def  stackTraceOrdinalWithMonitorOption : Option[RoaringBitmap] = None;
 
  
}