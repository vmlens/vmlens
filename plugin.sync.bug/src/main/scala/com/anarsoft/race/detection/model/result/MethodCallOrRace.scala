package com.anarsoft.race.detection.model.result

import com.vmlens.api.Icon


trait MethodCallOrRace {
  
  def threadId : Long;
  def methodCounter : Int;
  
  def comesBefore( other : MethodCallOrRace ) : Boolean;
  
  
  def name(modelFacadeAll : ModelFacadeAll) : String;
  def icon() : Icon; 
  
  
  def incrementSpace() : Boolean;
  
  def decrementSpace() : Boolean;
  
  
  
}

