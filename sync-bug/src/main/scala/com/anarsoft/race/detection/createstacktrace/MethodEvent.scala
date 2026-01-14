package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.createautomatictest.CreateAutomaticTestEvent
import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeEvent
import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId
import com.anarsoft.race.detection.stacktrace.StacktraceNode

trait MethodEvent extends WithMethodCounter 
  with EventWithLoopAndRunId 
  with CreateDominatorTreeEvent 
  with CreateAutomaticTestEvent {


  def createStacktraceNode(stack: StacktraceNodeStack): StacktraceNode;


}
