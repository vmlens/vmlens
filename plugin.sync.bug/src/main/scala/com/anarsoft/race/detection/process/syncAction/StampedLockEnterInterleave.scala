package com.anarsoft.race.detection.process.syncAction

import com.anarsoft.race.detection.process.interleave.StatementVisitor
import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait StampedLockEnterInterleave extends SyncActionLockEnter with InterleaveEventStatement with  InterleaveNamesAsStamped {
  
      def objectId = Some("<" +  monitorId  + ">");   
  
  
    def isSame(interleaveEventStatement :  InterleaveEventStatement) =
       {
         if( interleaveEventStatement.isInstanceOf[StampedLockEnterInterleave] )
         {
           val other = interleaveEventStatement.asInstanceOf[StampedLockEnterInterleave];
           
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
      if( interleaveEventStatement.isInstanceOf[StampedLockEnterInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[StampedLockEnterInterleave] 
        
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