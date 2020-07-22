package com.anarsoft.race.detection.process.volatileField

import com.vmlens.api.MemoryAccessType;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import com.anarsoft.race.detection.process.aggregate._;
import com.anarsoft.race.detection.process.syncAction.SyncAction
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinal
import com.anarsoft.race.detection.process.aggregate.WithLocationInClass
import com.anarsoft.race.detection.process.field.EventField;
import com.anarsoft.race.detection.process.partialOrder.SyncPointGeneric
import com.anarsoft.race.detection.model.result._;
import com.anarsoft.race.detection.model.WithStatementPosition
import com.anarsoft.race.detection.process.setMonitorInfo.EventSetMonitorInfo
import com.anarsoft.race.detection.process.monitorRelation.MonitorIdBlockIdStackTraceOrdinal;
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId
import com.vmlens.api.MemoryAccessType;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import com.vmlens.api.MemoryAccessType
import com.vmlens.api.internal.IconRepository

abstract class VolatileArrayAccessEvent  extends  SyncAction 
with EventSetStacktraceOrdinal   
with  SyncPointGeneric[VolatileArrayAccessId]      {
  
 //  def objectId = Some(objectHashCode);
  
   def methodId  : Int;
  
  def isStatic = false;
  
  def idPerMemoryLocation = VolatileArrayAccessId(objectHashCode,index)
  
  def compareIdPerMemoryLocation(other: VolatileArrayAccessId) =
  {
    if( other.objectHashCode != objectHashCode )
    {
       java.lang.Long.compare(objectHashCode,  other.objectHashCode)
    }
    else
    {
             java.lang.Long.compare(index,  other.index );
    }
    
    
  }
  def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     stackTraceOrdinal = in.ordinal;
   }
  
   
    def methodIdOption  = Some(methodId);
  def isStackTraceIncompleteOption  = None;
  
  var stackTraceOrdinal: Int;
  
     def eventStartsHappensBeforeRelation() = 
   {
     
     if( MemoryAccessType.isAtomic(operation) )
     {
       true;
     }
     else
     {
        MemoryAccessType.isWriteOnly(operation)
     }
     
   }
  def eventEndsHappensBeforeRelation() = 
  {
      if( MemoryAccessType.isAtomic(operation) )
     {
       true;
     }
     else
     {
        ! MemoryAccessType.isWriteOnly(operation)
     }
  }
  
    def accept(visitor : EventSetStacktraceOrdinalVisitor)
    {
         visitor.visit(this);
    }
  
    
     def objectHashCode : Long;
     def index : Long;
  
     def operation : Int;
     
     
     
  def icon()  =   IconRepository.getIconForOperationAndHasRace( new MemoryAccessType(operation) , false );
  
  
    def methodModel(modelFacade : ModelFacadeAll) = modelFacade.stackTraceGraph.getMethodModelForStackTraceNodeOrdinal(new StackTraceOrdinal(stackTraceOrdinal));
  
   def operationText(modelFacade : ModelFacadeAll)  = MemoryAccessType.getName(operation) + " Unsafe or VarHandle array access";
  
  
//   def create(modelFacade : ModelFacadeAll,prefix : Int , context : ContextLastRun) =
//   {
//     val methodDesc =   new StackTraceOrdinal(stackTraceOrdinal).getMethodDescription(modelFacade.stackTraceGraph)   //modelFacade.stackTraceGraph.
//    
//     
//   
//     
//     val a =  new ReportStatementFieldAccess(  operation ,  false , "Unsafe or VarHandle array access" , methodDesc , threadId , context );
//     new ReportParallizedStatementBlock( Some(a) , None , None , None , stackTraceOrdinal , modelFacade);
//     
//     
//   }
//     
     
}