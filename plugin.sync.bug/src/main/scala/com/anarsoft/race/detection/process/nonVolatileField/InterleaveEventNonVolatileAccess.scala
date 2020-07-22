package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.anarsoft.race.detection.model.result.LocationInClass
import com.vmlens.api.internal.reports.element.ReportStatementBlock;
import com.vmlens.api.internal.reports.element.ContextLastRun
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.model.result._
import com.vmlens.api.internal.reports.element._;
import com.vmlens.api.MemoryAccessType


trait InterleaveEventNonVolatileAccess extends InterleaveEventStatement {
  
  def getLocationInClass() : LocationInClass;
  def operation : Int;
  def stackTraceOrdinal  : Int;
  def programCounter : Int;
  def threadId : Long;
  
  def showSharedMemory  : Boolean;
    
  
  
//  
//      def create(modelFacade : ModelFacadeAll,prefix : Int , context : ContextLastRun) =
//   {
//     val methodDesc =   new StackTraceOrdinal(stackTraceOrdinal).getMethodDescription(modelFacade.stackTraceGraph)   //modelFacade.stackTraceGraph.
//     val fieldName =   getLocationInClass().getNameWithBold( modelFacade.fieldAndArrayFacade  , modelFacade.stackTraceGraph);
//     
//   
//     
//     val a =  new ReportStatementFieldAccess(  operation ,  true , fieldName , methodDesc , threadId , context );
//     new ReportParallizedStatementBlock( Some(a) , None , None , None  , stackTraceOrdinal , modelFacade);
//     
//     
//   }
//  
      
        def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;
        
        
    def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }
        
  
}