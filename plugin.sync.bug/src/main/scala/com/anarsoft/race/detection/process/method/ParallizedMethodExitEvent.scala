package com.anarsoft.race.detection.process.method


import com.anarsoft.race.detection.model.result.StackTraceOrdinal

trait ParallizedMethodExitEvent  extends MethodEvent {
  
  def isParallized() = true;
  def isMethodEnter() = false;
  
   def getParallizeId() =  None;
  
  
 
}