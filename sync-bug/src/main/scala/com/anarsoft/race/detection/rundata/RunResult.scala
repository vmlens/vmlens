package com.anarsoft.race.detection.rundata

import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.warning.Warning

trait RunResult {

  def foreach(f: EventForReportElement => Unit): Unit;
  def isFailure : Boolean;
  def dataRaceCount : Int;
  def runId : Int;
  def hasWarningForRun : Boolean

  def  warningIdList : Set[Warning];

  def takeThisInsteadOther(other : RunResult) : Boolean = {
    /*
     * we can have only one run with failure
     * so there is no need to look for the case that both are failure
     */
    if(isFailure) {
      true
    }
    else if(other.isFailure)  {
      false
    } else if(dataRaceCount != other.dataRaceCount) {
      dataRaceCount > other.dataRaceCount
    } else if (hasWarningForRun != other.hasWarningForRun) {
      if(hasWarningForRun) {
        true
      } else {
        false
      }
    }
    else {
      runId > other.runId
    }
    }

}
