package com.anarsoft.race.detection.model.result

import scala.collection.mutable.HashSet

class MethodWithMonitor(val methodOrdinal: MethodOrdinal, val group: Int,
    val stackTraceOrdinalSet : Set[StackTraceOrdinal],val methodContainsMonitor : Boolean , 
    val isThreadSafe : Boolean) extends MethodWithX {
  
  
  def calledMethodContainsMonitor( modelFacade: ModelFacadeMonitor) =
  {
    var result = false;
    
    for( s <- stackTraceOrdinalSet )
    {
      result = modelFacade.stackTraceGraphMonitorAnnotation.hasMonitor(s) | result;
    }
    
    result;
    
  }
  
  
  
}