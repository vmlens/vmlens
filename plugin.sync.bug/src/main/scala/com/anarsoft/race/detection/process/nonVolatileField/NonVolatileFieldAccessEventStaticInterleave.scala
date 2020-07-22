package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait NonVolatileFieldAccessEventStaticInterleave  extends NonVolatileFieldAccessEventStatic with InterleaveEventNonVolatileAccess {
  
    def objectId = None;
  
  
   def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[NonVolatileFieldAccessEventStaticInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[NonVolatileFieldAccessEventStaticInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.getLocationInClass() == getLocationInClass() && other.operation == operation  )
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