package com.anarsoft.race.detection.loopResult

import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.reportbuilder.EventForReportElement

import scala.collection.mutable.ArrayBuffer

class LoopResultList(val loopId : Int) extends LoopResult  {
  
  private val resultList = new ArrayBuffer[RunResult];
  
  override def foreach(f: EventForReportElement => Unit): Unit = {
    for(result <- resultList) {
      result.foreach(f);
    }
  }

  override def isFailure: Boolean = false

  override def dataRaceCount: Int = 0

  override def warningIdList: Set[Int] = Set();

  override def add(runResult: RunResult): Unit = {
    resultList.append(runResult);
  }

  override def count: Int = 0
}
