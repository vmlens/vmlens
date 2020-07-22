package com.anarsoft.race.detection.process.state

import com.vmlens.trace.agent.bootstrap.event.gen.StateEventFieldGen;

trait StateEventInitialField extends StateEventInitial {
  
  def  threadIdAtEvent  : Long
  def classId  : Int
  def  methodId  : Int
  def  methodCounter  : Int
  def  operation  : Int
  def  objectHashCode  : Long
  def slidingWindowIdAtEvent  : Int
  
  
  
  def createJavaEvent() =
  {
    new     StateEventFieldGen( slidingWindowIdAtEvent  ,        threadIdAtEvent
,        classId
,        methodId
,        methodCounter
,        operation
,        objectHashCode  );

 
  }
  
}