package com.anarsoft.race.detection.loopResult

import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.reportbuilder.EventForReportElement

import scala.collection.mutable.ArrayBuffer

trait LoopResult  {

  def loopId : Int;
  def foreach(f: EventForReportElement => Unit) : Unit;
  def isFailure: Boolean;
  def dataRaceCount: Int;
  def warningIdList: Set[Int];
  def add(runResult : RunResult) : Unit;
  def count : Int;

}

