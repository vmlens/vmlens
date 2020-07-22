package com.anarsoft.race.detection.process.scheduler

import com.vmlens.api.internal.reports.element._;
import com.vmlens.api.internal.reports.element.ContextLastRun
import com.anarsoft.race.detection.model.result.ModelFacadeAll
import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.vmlens.api.internal.IconRepository
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait MethodCallbackExitEvent  extends XMethodEvent {
       
    def objectId = None;   
  
     def methodIdOption               = None;

     
                   
               def  getOpText() = "Callback Exit"
    
               
     def icon()  =  IconRepository.CALLBACK_EXIT
        
                      def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }
  
               
   def canStartLoop() = false;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[MethodCallbackExitEvent] )
      {
        val other= interleaveEventStatement.asInstanceOf[MethodCallbackExitEvent] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal  )
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