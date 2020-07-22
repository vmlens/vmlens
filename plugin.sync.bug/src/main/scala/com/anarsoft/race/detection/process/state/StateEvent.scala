package com.anarsoft.race.detection.process.state

import com.anarsoft.race.detection.process.setStacktraceOrdinal._;
import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId

trait StateEvent extends EventSetStacktraceOrdinal  {
  
  
   def methodId : Int;
  
   def methodIdOption               = Some(methodId);
   def isStackTraceIncompleteOption = None;
   
   
  
  
   
     var stackTraceOrdinal = -1;
 
   
  
   def setStackTraceOrdinal(in: StackTraceOrdinalAndMethodId)
   {
     
     stackTraceOrdinal = in.ordinal;
    
     
   }
   
   def accept(visitor : StateEventVisitor);
   
   
}