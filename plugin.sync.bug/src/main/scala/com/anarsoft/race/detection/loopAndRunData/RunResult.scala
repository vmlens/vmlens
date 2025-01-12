package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.reportbuilder.EventForReportElement

trait RunResult extends Ordered[RunResult] {

  def foreach(f: EventForReportElement => Unit): Unit;

  def isFailure: Boolean;

  def dataRaceCount: Int;

  def loopId: Int;

  def runId: Int;;
  
}
