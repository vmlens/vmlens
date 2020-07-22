package com.anarsoft.race.detection.process.method


import com.anarsoft.race.detection.model.result.StackTraceOrdinal


trait MethodEnterEvent  extends MethodEvent {
  
   def isMethodEnter() = true;
   def isParallized() = false;
  
   
    //  MethodEnter( val threadId : Long,val methodCounter : Int,val stackTraceOrdinal : Int)
  
    
     def getParallizeId() = None;
    
}