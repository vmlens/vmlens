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
        if (runResult.compare(x.runResult) > 0) {
          loopIdToResult.put(loopId, RunCountAndResult(count, runResult))
        } else {
          loopIdToResult.put(loopId, RunCountAndResult(count, x.runResult))
        }
      }

      case None => {
        loopIdToResult.put(loopId, RunCountAndResult(runResult.runId, runResult))
      }
    }
  }


  def build() : Seq[RunCountAndResult] = loopIdToResult.values.toSeq;

}
