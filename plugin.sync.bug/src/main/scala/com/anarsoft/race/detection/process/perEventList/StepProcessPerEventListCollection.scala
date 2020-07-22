package com.anarsoft.race.detection.process.perEventList

import scala.collection.mutable.ArrayBuffer
import com.anarsoft.race.detection.process.workflow.SingleStep

class StepProcessPerEventListCollection[CONTEXT]( val contextClass : Class[CONTEXT]) extends SingleStep[CONTEXT] {
  
  
  val processPerEventListCollection = new ArrayBuffer[PerEventListTrait[CONTEXT]]
  
  
   def execute(context : CONTEXT)
  {
    for( elem <- processPerEventListCollection )
    {
      elem.process(context);
    }
    
    
    
  }
  
  
  
    override  def getStepName() = getClass().getCanonicalName()+ "@" + contextClass.getCanonicalName(); 
  
}