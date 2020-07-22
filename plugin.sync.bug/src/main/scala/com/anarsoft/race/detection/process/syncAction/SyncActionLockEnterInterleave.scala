package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement



trait SyncActionLockEnterInterleave extends SyncActionLockEnter with InterleaveEventStatement with InterleaveNamesAsLock {
    
    def objectId = Some("<" +  monitorId  + ">");   
  
  
       def isSame(interleaveEventStatement :  InterleaveEventStatement) =
       {
         if( interleaveEventStatement.isInstanceOf[SyncActionLockEnterInterleave] )
         {
           val other = interleaveEventStatement.asInstanceOf[SyncActionLockEnterInterleave];
           
           other.monitorId == monitorId && other.threadId == threadId //&& other.stackTraceOrdinal == stackTraceOrdinal
           
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
        
         
    def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[SyncActionLockEnterInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[SyncActionLockEnterInterleave] 
        
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