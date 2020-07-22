package com.anarsoft.race.detection.process.method

import  com.anarsoft.race.detection.model.method.StackTraceOrdinalAndMethodId

trait MethodFlowPerThread {
  
    def getStackTraceOrdinal(methodCounter : Int , methodId : Int , stackTraceTree : StackTraceTree ) : StackTraceOrdinalAndMethodId;
    def getStackTraceOrdinal(methodCounter : Int , methodId : Int , stackTraceIncomplete : Boolean , stackTraceTree : StackTraceTree ) : StackTraceOrdinalAndMethodId;
    def getStackTraceOrdinal(methodCounter : Int , stackTraceTree : StackTraceTree  ) : StackTraceOrdinalAndMethodId;
    
    
   def hasStackTraceOrdinal(methodCounter : Int) : Boolean;
  
}