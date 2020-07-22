package com.anarsoft.race.detection.process.method

import scala.collection.mutable.HashMap;
import com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId

class MethodFlow {
  
  val threadId2Flow = new HashMap[Long,MethodFlowPerThreadImpl]();
  
  
  def createMethodFlowBlock(threadId : Long, start : StackTraceOrdinalAndMethodId) =
  {
    
    
    threadId2Flow.get(threadId) match
    {
      
      case None =>
        {
          val x = new MethodFlowPerThreadImpl();
          threadId2Flow.put(threadId, x  );
          
          
          x.createMethodFlowBlock(start);
        }
      
      
      case Some(x) =>
        {
          x.createMethodFlowBlock(start);
        }
      
    }
    
    
    
  }
  
  
  def getMethodFlowPerThread( threadId : Long ) =
  {
    threadId2Flow.get(threadId) match
    {
      case Some(x) => x;
      
      case None =>
        {
          new MethodFlowPerThreadEmpty();
        }
        
      
      
      
    }
  }
  
  
  
}