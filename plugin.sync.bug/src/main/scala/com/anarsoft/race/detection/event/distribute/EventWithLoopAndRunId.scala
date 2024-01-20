package com.anarsoft.race.detection.event.distribute

trait EventWithLoopAndRunId {
  def loopId: Int;

  def runId: Int;
}
