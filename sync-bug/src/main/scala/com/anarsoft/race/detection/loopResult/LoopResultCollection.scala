package com.anarsoft.race.detection.loopResult

import com.anarsoft.race.detection.loopAndRunData.RunResult
import com.anarsoft.race.detection.process.main.{LoopReportBuilder, RunCountAndResult}

import scala.collection.mutable

class LoopResultCollection {

  private val loopIdToResult = new mutable.HashMap[Int, RunCountAndResult]

  def put(runResult: RunResult): Unit = {
    val loopId = runResult.loopId

    loopIdToResult.get(loopId) match {
      case Some(x) => {
        val count = Math.max(x.count, runResult.runId)
      
        if(runResult.isFailure) {
          loopIdToResult.put(loopId, RunCountAndResult(count, runResult))
        } else if (x.runResult.isFailure) {
          loopIdToResult.put(loopId, RunCountAndResult(count, x.runResult))
        } else if(runResult.dataRaceCount > x.runResult.dataRaceCount) {
          loopIdToResult.put(loopId, RunCountAndResult(count, runResult)) 
        }  else if(x.runResult.dataRaceCount > runResult.dataRaceCount) {
          loopIdToResult.put(loopId, RunCountAndResult(count, x.runResult))
        } else if(runResult.warningIdList.size >  x.runResult.warningIdList.size) {
          loopIdToResult.put(loopId, RunCountAndResult(count, runResult))
        } else if (x.runResult.warningIdList.size > runResult.warningIdList.size) {
            loopIdToResult.put(loopId, RunCountAndResult(count, x.runResult))
          } else {
          // currently through a problem in the classloading filter
          // ThreadLocalForParallelizeSingleton.canProcess return false
          // typically for the first run so we filter it here
          if(x.runResult.runId == 0) {
            loopIdToResult.put(loopId, RunCountAndResult(count, runResult))
          } else {
            loopIdToResult.put(loopId, RunCountAndResult(count, x.runResult))
          }


        }
        
      }

      case None => {
        loopIdToResult.put(loopId, RunCountAndResult(runResult.runId, runResult))
      }
    }
  }


  def build() : Seq[RunCountAndResult] = loopIdToResult.values.toSeq;

}
