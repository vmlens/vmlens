package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait ArrayAccessEventInterleave extends ArrayAccessEvent with InterleaveEventNonVolatileAccess {
  
  
   def objectId = Some("<" +  objectHashCode  + ">");
  
    def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[ArrayAccessEventInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[ArrayAccessEventInterleave] 
        
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