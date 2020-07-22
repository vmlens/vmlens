package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.process.scheduler.SchedulerEvent

trait LoopOrRunEvent extends SchedulerEvent {
  
  def loopId : Int;
  def compare( other :  LoopOrRunEvent) : Int;
  def accept[RESULT](  visitor : LoopOrRunEventVisitor[RESULT] ) : RESULT;
  
  
}