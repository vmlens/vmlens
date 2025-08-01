package com.anarsoft.race.detection.loopResult

import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.reportbuilder.EventForReportElement

class LoopResultSingle(val loopId : Int,
                       var runResult : RunResult, 
                       var count : Int) extends LoopResult {

  override def foreach(f: EventForReportElement => Unit): Unit = {
    runResult.foreach(f)
  }

  override def isFailure: Boolean = runResult.isFailure;

  override def dataRaceCount: Int = runResult.dataRaceCount

  override def warningIdList: Set[Int] = runResult.warningIdList

  override def add(newRunResult: RunResult): Unit = {
     count = Math.max(count, newRunResult.runId)
    if (newRunResult.isFailure) {
       runResult = newRunResult;
    } else if (runResult.isFailure) {
      
    } else if (newRunResult.dataRaceCount > runResult.dataRaceCount) {
      runResult = newRunResult;
    } else if (runResult.dataRaceCount > newRunResult.dataRaceCount) {
   
    } else if (newRunResult.warningIdList.size > runResult.warningIdList.size) {
      runResult = newRunResult;
    } else if ( runResult.warningIdList.size > newRunResult.warningIdList.size ) {
     
    } else {
      // currently through a problem in the classloading filter
      // ThreadLocalForParallelizeSingleton.canProcess return false
      // typically for the first run so we filter it here
      if (runResult.runId == 0) {
        runResult = newRunResult;
      }
    }
  }
}
