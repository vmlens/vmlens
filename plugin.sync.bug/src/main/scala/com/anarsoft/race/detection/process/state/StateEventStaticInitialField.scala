package com.anarsoft.race.detection.process.state

import com.vmlens.trace.agent.bootstrap.event.gen.StateEventStaticFieldGen;


trait StateEventStaticInitialField extends StateEventInitial {
  
    def  threadIdAtEvent  : Long
  def fieldId  : Int
  def  methodId  : Int
  def  methodCounter  : Int
  def  operation  : Int
  def slidingWindowIdAtEvent  : Int
  
  
  
  def createJavaEvent() =
  {
  new  StateEventStaticFieldGen(
 slidingWindowIdAtEvent 
,        threadIdAtEvent
,        fieldId
,        methodId
,        methodCounter
,        operation
)

 
  }
  
  
}