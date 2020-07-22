package com.anarsoft.race.detection.process.volatileField

import com.vmlens.api.MemoryAccessType;
import com.anarsoft.race.detection.process.monitorRelation.MonitorInfo
import com.anarsoft.race.detection.process.setMonitorInfo.ContextSetMonitorInfo;
import com.anarsoft.race.detection.process.setStacktraceOrdinal.EventSetStacktraceOrdinalVisitor
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._;


trait VolatileAccessEventStatic extends VolatileAccessEventAbstract[Int]   {
  
  
   def objectId =  None;
  
  def idPerMemoryLocation = fieldId;
  def compareIdPerMemoryLocation(  other : Int)  = java.lang.Integer.compare(idPerMemoryLocation, other);
    def isStatic = true;
  
 def eventStartsHappensBeforeRelation() = isWrite;
  def eventEndsHappensBeforeRelation() = ! isWrite;
  
  def isWrite() : Boolean;
  
  
    def operation() =
  {
    if( isWrite )
    {
      MemoryAccessType.IS_WRITE
    }
    else
    {
      MemoryAccessType.IS_READ
    }
    
  }
  
   
  def accept(visitor : EventSetStacktraceOrdinalVisitor)
    {
      visitor.visit(this);
    }
    
  
  
  
//   def create(modelFacade : ModelFacadeAll,prefix : Int,context : ContextLastRun) =
//   {
//     val methodDesc =   new StackTraceOrdinal(stackTraceOrdinal).getMethodDescription(modelFacade.stackTraceGraph)   //modelFacade.stackTraceGraph.
//     val fieldName =   getLocationInClass().getNameWithBold( modelFacade.fieldAndArrayFacade , modelFacade.stackTraceGraph );
//     
//  
//     
//     
//     val a =  new ReportStatementFieldAccess(  operation() , false ,  fieldName , methodDesc , threadId , context );
//     new ReportParallizedStatementBlock( Some(a)  , None , None , None, stackTraceOrdinal , modelFacade );
//     
//     
//   }
  
  
    
}