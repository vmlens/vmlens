package com.anarsoft.race.detection.model.result





class MethodWithSharedState(val methodOrdinal: MethodOrdinal, val group: Int,val stackTraceOrdinalSet : Set[StackTraceOrdinal],val isThreadSafe : Boolean ,val isStateless : Boolean,val stateNotFiltered : Boolean) extends MethodWithX {

  
 def memoryAggregateSet(modelFacadeState : ModelFacadeState) = modelFacadeState.stackTraceGraphStateAnnotation.memoryAggregateSet(stackTraceOrdinalSet);
  
  

 

}