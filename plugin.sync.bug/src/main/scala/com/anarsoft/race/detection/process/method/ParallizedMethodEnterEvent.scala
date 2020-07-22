package com.anarsoft.race.detection.process.method


import com.anarsoft.race.detection.model.result.StackTraceOrdinal

trait ParallizedMethodEnterEvent extends MethodEvent{ 
  
  
   var methodOrdinal = -1;
  
  
   def isParallized() = true;
   def isMethodEnter() = true;
 
   
   def parallizeId  : Int;
   
    
   
    def getParallizeId() = Some(parallizeId);
   
}