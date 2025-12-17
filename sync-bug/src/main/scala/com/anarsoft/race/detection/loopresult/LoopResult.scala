package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.rundata.RunResult
import com.anarsoft.race.detection.warning.Warning


trait LoopResult  {

  def loopId : Int;
  def foreach(f: EventForReportElement => Unit) : Unit;
  def isFailure: Boolean;
  def dataRaceCount: Int;
  def warningIdList: Set[Warning];
  def add(runResult : RunResult) : Unit;
  def count : Int;

}

