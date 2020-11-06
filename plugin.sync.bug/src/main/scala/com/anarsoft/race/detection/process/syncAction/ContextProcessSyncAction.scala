package com.anarsoft.race.detection.process.syncAction

import java.util.ArrayList;
import scala.collection.mutable.ArrayBuffer;
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.partialOrder.PartialOrderBuilder;
import com.anarsoft.race.detection.process.read._;
import com.anarsoft.race.detection.process.gen._;
import com.anarsoft.race.detection.process.method._;
import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.process.field._;
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric;
import com.anarsoft.race.detection.process.partialOrder.PartialOrder
import com.anarsoft.race.detection.process.monitor.MonitorEvent
import com.anarsoft.race.detection.process.volatileField._;
import com.anarsoft.race.detection.model.description.ThreadNames
import com.anarsoft.race.detection.process.monitor.ContextMonitor
import com.anarsoft.race.detection.process.partialOrder.ContextCreatePartialOrder
import com.anarsoft.race.detection.process.interleave.InterleaveEventList


trait ContextProcessSyncAction extends ContextVolatileField  with ContextMonitor  with ContextCreatePartialOrder {
  
  
    def  interleaveEventList : InterleaveEventList;
  
    var syncActionStreams  :   StreamAndStreamStatistic[SyncAction] = null;
    var threadStopList : ArrayList[ThreadStoppedEventGen] = null;
    var prevoiusStartHappensBeforeMap : PrevoiusStartHappensBeforeMap[SyncPointGeneric[_]] = null;
  

    def monitorEventList     :    ArrayList[MonitorEvent];
    
    var lockEventList        :   ArrayList[SyncActionLock] = null;
    var volatileAccessEventList : ArrayList[VolatileAccessEvent] = null;
    
    var volatileAccessArrayEventList : ArrayList[VolatileArrayAccessEvent] = null;
    
    var volatileAccessEventStatic : ArrayList[VolatileAccessEventStatic] = null; 
    
    
  
    def methodFlow : MethodFlow;
    def stackTraceTree : StackTraceTree; 
    def threadNames : ThreadNames;
    


   
    def threadId2LastProgramCounter : HashMap[Long,Int] ;
    
//    def statementList : ArrayList[Statement];
//    
//    var syncStatementList : ArrayList[SyncStatement] = null;
    
    

    
    
    def resetContextProcessSyncAction()
    {
      lockEventList = new ArrayList[SyncActionLock];
      volatileAccessEventList = new ArrayList[VolatileAccessEvent]();
      volatileAccessEventStatic = new ArrayList[VolatileAccessEventStatic]();
      
      volatileAccessArrayEventList = new  ArrayList[VolatileArrayAccessEvent]();
      
    }
    
     
   def initializeContextSyncAction()
   {
         threadStopList  = new ArrayList[ThreadStoppedEventGen]()
    
      
         
         prevoiusStartHappensBeforeMap = PrevoiusStartHappensBeforeMap.create4SyncPointGeneric( threadNames.id2ThreadName )
         
//      syncStatementList = new ArrayList[SyncStatement];

   }
   
  
  
}