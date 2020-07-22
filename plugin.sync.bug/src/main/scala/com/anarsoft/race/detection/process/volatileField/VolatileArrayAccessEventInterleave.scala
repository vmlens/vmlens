package com.anarsoft.race.detection.process.volatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement
import com.anarsoft.race.detection.process.interleave.StatementVisitor

trait VolatileArrayAccessEventInterleave extends VolatileArrayAccessEvent with InterleaveEventStatement {
  
  
   def objectId = Some("<" +  objectHashCode  + ">");
   
          def isSame(InterleaveEventStatement :  InterleaveEventStatement)  = false;

                 def acceptStatementVisitor(statementVisitor : StatementVisitor)    
    {
      statementVisitor.visit(this);
    }    


      def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[VolatileArrayAccessEventInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[VolatileArrayAccessEventInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.methodId == methodId  )
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