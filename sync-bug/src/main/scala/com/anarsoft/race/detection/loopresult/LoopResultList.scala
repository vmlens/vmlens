package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.rundata.RunResult
import com.anarsoft.race.detection.warning.Warning

import scala.collection.mutable
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

  override def warningIdList: Set[Warning] = {
    val warnings  = new mutable.HashSet[Warning]();
    for (result <- resultList) {
      warnings.addAll(result.warningIdList);
    }
    warnings.toSet;
  }

  override def add(runResult: RunResult): Unit = {
    resultList.append(runResult);
  }

  override def count: Int = resultList.size - 1;
}
