package com.anarsoft.race.detection.process.state

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;

trait StateEventInitial {
  
  def slidingWindowIdAtEvent : Int;
  
  def createJavaEvent() : RuntimeEvent;
  
  
}