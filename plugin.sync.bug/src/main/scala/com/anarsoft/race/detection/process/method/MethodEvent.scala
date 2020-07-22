package com.anarsoft.race.detection.process.method


import com.anarsoft.race.detection.model.result.StackTraceOrdinal


trait MethodEvent extends  ApplyMethodEventVisitor  {
  
  def threadId : Long;
  def methodCounter : Int;
  
  def isMethodEnter() : Boolean;
  def isParallized() : Boolean;
  
  def methodId() : Int;
  
  
  def getParallizeId() : Option[Int];
  
  

  
}