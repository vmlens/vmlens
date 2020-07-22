package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait SyncActionLockExitInterleave extends SyncActionLockExit with InterleaveEventStatement  with InterleaveNamesAsLock  {
       
    def objectId = Some("<" +  monitorId  + ">");   
     
      def isSame(interleaveEventStatement :  InterleaveEventStatement) =
       {
         if( interleaveEventStatement.isInstanceOf[SyncActionLockExitInterleave] )
         {
           val other = interleaveEventStatement.asInstanceOf[SyncActionLockExitInterleave];
           
           other.monitorId == monitorId  && other.threadId == threadId//&& other.stackTraceOrdinal == stackTraceOrdinal
           
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
       
       
    def canStartLoop() = false;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[SyncActionLockExitInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[SyncActionLockExitInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal &&  other.monitorId == monitorId  )
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