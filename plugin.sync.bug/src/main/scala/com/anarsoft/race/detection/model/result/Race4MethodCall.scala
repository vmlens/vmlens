package com.anarsoft.race.detection.model.result

import com.vmlens.api.internal.IconRepository
import com.vmlens.api.MemoryAccessType
import  com.anarsoft.race.detection.process.detectRaceConditions.EventWrapperDetectRaceConditions

case class Race4MethodCall(val methodCounter : Int, val threadId : Long,  val locationInClass : LocationInClass , val  isWrite : Boolean) extends MethodCallOrRace  {
  
  
  
   def incrementSpace()= false;
  
  def decrementSpace() = false;
  
  
   def name(modelFacade : ModelFacadeAll) =   modelFacade. fieldAndArrayFacade.getQualifiedFieldName(locationInClass,modelFacade.stackTraceGraph) //getQualifiedFieldName(viewTyp.modelFacade.stackTraceGraph);


  def icon()  = {
     if(isWrite)
          {
            IconRepository.getIconForOperationAndHasRace(  new  MemoryAccessType (MemoryAccessType.IS_WRITE), true)
          }
          else
          {
            IconRepository.getIconForOperationAndHasRace( new  MemoryAccessType (MemoryAccessType.IS_READ), true);
          }
  }
  
  
  
  
  
    def comesBefore( other : MethodCallOrRace ) =
  {
     other match
     {
       case x : 
       Race4MethodCall =>
         {
           false;
         }
       
       case y : MethodCall  =>
       {
         y.isEnter;
       }
       
     }
 
   }
  
  
}

object Race4MethodCall
{
  
  def apply(event : EventWrapperDetectRaceConditions ,isWrite : Boolean ) =
  {
    new Race4MethodCall(  event.methodCounter , event.threadId , event.getLocationInClass() , isWrite );
  }
  
  
  
}




