package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.loopAndRunData.RunResult

class RunResultMock(eventForRunElements: List[EventForRunElement], val isFailure: Boolean,
                    val dataRaceCount: Int) extends RunResult {

  override def foreach(f: EventForRunElement => Unit): Unit = {
    eventForRunElements.foreach(f);
  }

  override def loopId: Int = 0

  override def runId: Int = 0

  override def compare(that: RunResult): Int = 0
}
