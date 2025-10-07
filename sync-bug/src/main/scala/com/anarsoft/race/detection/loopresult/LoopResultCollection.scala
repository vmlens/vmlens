package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.rundata.RunResultImpl

import scala.collection.mutable

class LoopResultCollection(val useList : Boolean) {

  private val loopIdToResult = new mutable.HashMap[Int, LoopResult]

  def put(runResult: RunResultImpl): Unit = {
    val loopId = runResult.loopId

    loopIdToResult.get(loopId) match {
      case Some(x) => {
        x.add(runResult)
      }
      case None => {
        if(useList) {
          val loopResultList = new LoopResultList(loopId);
          loopResultList.add(runResult)
          loopIdToResult.put(loopId, loopResultList)
        } else {
          loopIdToResult.put(loopId,  LoopResultSingle(loopId, runResult, runResult.runId))
        }
      }
    }
  }

  def build(): Seq[LoopResult] = loopIdToResult.values.toSeq;

}
