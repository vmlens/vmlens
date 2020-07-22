package com.anarsoft.race.detection.model.result

import com.anarsoft.race.detection.process.method.MethodEvent
import  com.anarsoft.race.detection.model.graph.ModelKey2OrdinalMap
import com.vmlens.api.internal.IconRepository

case class MethodCall(val methodOrdinal : Int ,val methodCounter : Int , val threadId : Long, val isEnter : Boolean)  extends MethodCallOrRace {
  
   def incrementSpace()= isEnter;
  
  def decrementSpace() = ! isEnter;
  
  
  
  
  

  def comesBefore( other : MethodCallOrRace ) =
  {
    
    isEnter;
    
   }
    
  
   def name(facade : ModelFacadeAll) = facade.stackTraceGraph.getMethodModelForOrdinal(methodOrdinal).getFullNameWithBoldName();
   
   def icon() = if(isEnter)
   {
     IconRepository.METHOD_ENTER
   }
   else
   {
    IconRepository.METHOD_EXIT
   }
  
  
   
  }





  
  

object MethodCall{
  
  def apply(event : MethodEvent , methodId2Ordinal :  ModelKey2OrdinalMap[Int]) =
  {
       val ordinal = methodId2Ordinal.getOrAddOrdinal(event.methodId())
    
    
    new MethodCall(ordinal , event.methodCounter , event.threadId , event.isMethodEnter());
  }
  
  
  
}