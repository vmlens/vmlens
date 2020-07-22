package com.anarsoft.race.detection.process.monitor

import  com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor

trait MonitorExitEventInterleave extends MonitorExitEvent with InterleaveEventStatement {
  
      def objectId = Some("<" +  monitorId  + ">");   
  
   def  raceAt()  = None;
       def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;
       
       
       
                 def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }    
                 
                 
                 
                 
                 def canStartLoop() = false;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[MonitorExitEventInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[MonitorExitEventInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.monitorId == monitorId )
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