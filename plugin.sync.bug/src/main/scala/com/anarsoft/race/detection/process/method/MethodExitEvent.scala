package com.anarsoft.race.detection.process.method


import com.anarsoft.race.detection.model.result.StackTraceOrdinal

trait MethodExitEvent extends MethodEvent {
  
   def isMethodEnter() = false;
   def isParallized() = false;

    
    
   def getParallizeId() = None;
}