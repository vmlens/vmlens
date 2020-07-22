package com.anarsoft.race.detection.process.volatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor

trait VolatileAccessEventInterleave extends VolatileAccessEvent with InterleaveEventStatement {
    
 def objectId = Some("<" +  objectHashCode  + ">");
  
          def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;
          
          
          
          
                 def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }   
                 
                 
                 
     def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[VolatileAccessEventInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[VolatileAccessEventInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.getLocationInClass() == getLocationInClass() && other.operation()  == operation() )
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