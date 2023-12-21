package com.anarsoft.race.detection.event.load

trait EventWithLoopAndRunId {
  def loopId: Int;

  def runId: Int;
}
