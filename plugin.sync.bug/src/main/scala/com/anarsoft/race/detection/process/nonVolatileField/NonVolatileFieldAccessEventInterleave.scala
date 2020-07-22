package com.anarsoft.race.detection.process.nonVolatileField

import com.anarsoft.race.detection.process.interleave.InterleaveEventStatement

trait NonVolatileFieldAccessEventInterleave extends NonVolatileFieldAccessEvent  with InterleaveEventNonVolatileAccess {
  
  
    def objectId = Some("<" +  objectHashCode  + ">");
  
  def canStartLoop() = true;
    def isSameStatement(interleaveEventStatement :  InterleaveEventStatement) =
    {
      if( interleaveEventStatement.isInstanceOf[NonVolatileFieldAccessEventInterleave] )
      {
        val other= interleaveEventStatement.asInstanceOf[NonVolatileFieldAccessEventInterleave] 
        
        if( other.threadId == threadId && other.stackTraceOrdinal == stackTraceOrdinal && other.getLocationInClass() == getLocationInClass() && other.operation == operation )
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