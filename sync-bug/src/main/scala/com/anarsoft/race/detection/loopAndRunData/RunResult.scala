package com.anarsoft.race.detection.loopAndRunData

import com.anarsoft.race.detection.reportbuilder.EventForReportElement

trait RunResult  {

  def foreach(f: EventForReportElement => Unit): Unit;

  def isFailure: Boolean;

  def dataRaceCount: Int;

  def warningIdList : Set[Int];
  
  def loopId: Int;

  def runId: Int;;
  
}
