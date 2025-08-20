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

  override def isFailure: Boolean = {
    var isFailure = false;
    for(result <- resultList) {
      isFailure = isFailure | result.isFailure;
    }
    isFailure;
  }

  override def dataRaceCount: Int = {
    var maxCount = 0;
    for (result <- resultList) {
      maxCount = Math.max(maxCount,result.dataRaceCount);
    }
    maxCount;
  }

  override def warningIdList: Set[Int] = {
    var warnings: Set[Int] = Set();
    for (result <- resultList) {
      if(warnings.size < result.warningIdList.size) {
        warnings = result.warningIdList;
      }
    }
    warnings;
  }

  override def add(runResult: RunResult): Unit = {
    resultList.append(runResult);
  }

  override def count: Int = resultList.size
}
