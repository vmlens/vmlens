package com.anarsoft.race.detection.process.perEventList

import com.anarsoft.race.detection.process.workflow.SingleStep


class StepProcessSingleEventList[CONTEXT](val perEventList : PerEventListTrait[CONTEXT], val contextClass : Class[CONTEXT]) extends SingleStep[CONTEXT] {
  
   def execute(context : CONTEXT)
  {
    perEventList.process(context);

  }
   
   
    override  def getStepName() = getClass().getCanonicalName()+ "@" + contextClass.getCanonicalName(); 
  
  
}