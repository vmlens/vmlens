package com.anarsoft.race.detection.process.scheduler

import com.vmlens.api.internal.reports.element._;
import com.vmlens.api.internal.reports.element.ContextLastRun
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.model.result.MethodOrdinal
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.vmlens.api.internal.IconRepository
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait MethodAtomicExitEvent  extends XMethodEvent {
  

     def objectId = None;   
    
    
    def  hasCallback  : Byte;
    
    def methodId : Int;
    var methodOrdinal = -1;
    
     def methodIdOption               = Some(methodId);
    
     
      def  getOpText() =    if(hasCallback == 0)
        {
           "atomic"
        }
        else
        {
           "atomic exit" 
        }

    
     def icon()  = 
        if(hasCallback == 0)
        {
           IconRepository.METHOD
        }
        else
        {
          IconRepository.ATOMIC_EXIT
        }
     
    
     
     
     
        def canStartLoop() = false;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[MethodAtomicExitEvent] )
      {
        val other= interleaveEventStatement.asInstanceOf[MethodAtomicExitEvent] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal &&  other.methodId == methodId  )
        {
          true
        }
        else
        {
          false
        }
      }
      else
      {
        false;
      }
      
      
    }     
     
     
     

    
          def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }
    
}