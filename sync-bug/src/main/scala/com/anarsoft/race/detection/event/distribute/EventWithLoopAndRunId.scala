package com.anarsoft.race.detection.event.distribute

import com.anarsoft.race.detection.loopAndRunData.LoopAndRunId

trait EventWithLoopAndRunId {
  def loopId: Int;
  def runId: Int;
  
  def loopAndRunId()  : LoopAndRunId =  LoopAndRunId(loopId,runId);
  
}
