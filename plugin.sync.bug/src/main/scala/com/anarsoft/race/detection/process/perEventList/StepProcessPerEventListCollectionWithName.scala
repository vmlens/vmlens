package com.anarsoft.race.detection.process.perEventList

class StepProcessPerEventListCollectionWithName[CONTEXT](  contextClass : Class[CONTEXT],val name : String) extends StepProcessPerEventListCollection[CONTEXT](contextClass) {
  
 
  
  
    override  def getStepName() = name; 
}