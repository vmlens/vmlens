package com.anarsoft.race.detection.loopResult

import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.reportbuilder.EventForReportElement

class RunResultMock (val isFailure: Boolean,
                     val dataRaceCount: Int,
                     val runId : Int,
                     val warningIdList : Set[Int]) extends RunResult {

  override def foreach(f: EventForReportElement => Unit): Unit = {
  }


  override def loopId: Int = 0
  
  
  
}
