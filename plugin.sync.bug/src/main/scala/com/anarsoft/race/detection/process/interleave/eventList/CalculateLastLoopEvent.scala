package com.anarsoft.race.detection.process.interleave.eventList

import com.anarsoft.race.detection.process.interleave._;

class CalculateLastLoopEvent extends LoopOrRunEventVisitor[Unit] {
  
  var loopEndEvent : Option[LoopEndEvent] = None;
  var lastStartedRun = -1;
  
  
  def visit(event : LoopStartEvent) 
  {
    
  }
   
  def visit(event : LoopEndEvent) 
  {
   loopEndEvent = Some(event);
  }
  
  
  def visit(event : RunEndEvent) 
  {
    
  }
  
  
  def visit(event : RunStartEvent)
  {
    lastStartedRun= event.runId;
  }
  
  
}