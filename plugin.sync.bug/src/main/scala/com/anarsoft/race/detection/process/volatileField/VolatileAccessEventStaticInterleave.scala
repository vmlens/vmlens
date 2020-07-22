package com.anarsoft.race.detection.process.volatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor

trait VolatileAccessEventStaticInterleave extends VolatileAccessEventStatic with InterleaveEventStatement {
   
          def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;
          
          
          
                         def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }    
                         
                         
                         
      def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[VolatileAccessEventStaticInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[VolatileAccessEventStaticInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.getLocationInClass() == getLocationInClass()  )
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