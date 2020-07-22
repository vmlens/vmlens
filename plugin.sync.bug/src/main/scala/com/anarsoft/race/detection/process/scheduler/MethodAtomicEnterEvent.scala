package com.anarsoft.race.detection.process.scheduler

import com.vmlens.api.internal.reports.element._;
import com.vmlens.api.internal.reports.element.ContextLastRun
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.model.result.MethodOrdinal
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.vmlens.trace.agent.bootstrap.util.Constants
import com.vmlens.api.internal.IconRepository
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait MethodAtomicEnterEvent extends XMethodEvent {
  
     def objectId = None;   
  
    def methodId : Int;
    var methodOrdinal = -1;
    
       def methodIdOption               = Some(methodId);    
     def  hasCallback  : Byte;
  

    
      def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }
  
    def  getOpText() =    if(hasCallback == 0)
        {
           "atomic"
        }
        else
        {
           "atomic enter" 
        }
    

    
     def icon()  = 
        if(hasCallback == 0)
        {
           IconRepository.METHOD
        }
        else
        {
          IconRepository.ATOMIC_ENTER
        }
    
     
     
    def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[MethodAtomicEnterEvent] )
      {
        val other= interleaveEventStatement.asInstanceOf[MethodAtomicEnterEvent] 
        
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
     
     
     
     
     
     
     
     
     
}