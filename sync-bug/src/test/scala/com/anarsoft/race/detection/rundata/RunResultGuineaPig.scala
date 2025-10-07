package com.anarsoft.race.detection.rundata

import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.warning.Warning

class RunResultGuineaPig(val isFailure: Boolean,
                         val dataRaceCount: Int,
                         val  hasWarningForRun : Boolean,
                         val runId : Int) extends RunResult{

  override def foreach(f: EventForReportElement => Unit): Unit = {}
  override def warningIdList: Set[Warning] = Set();

}
