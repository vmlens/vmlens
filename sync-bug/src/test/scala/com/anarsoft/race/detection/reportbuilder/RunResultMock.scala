package com.anarsoft.race.detection.reportbuilder

import com.anarsoft.race.detection.loopAndRunData.RunResult

class RunResultMock(eventForRunElements: List[EventForReportElement], 
                    val isFailure: Boolean,
                    val dataRaceCount: Int, 
                    val warningIdList : Set[Int]) extends RunResult {

  override def foreach(f: EventForReportElement => Unit): Unit = {
    eventForRunElements.foreach(f);
  }
  
  
  override def loopId: Int = 0

  override def runId: Int = 0
  
}
