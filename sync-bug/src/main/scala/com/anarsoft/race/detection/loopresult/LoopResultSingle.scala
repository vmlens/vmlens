package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.dominatortree.DominatorTree
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.rundata.RunResult
import com.anarsoft.race.detection.warning.Warning

import scala.collection.mutable

class LoopResultSingle(val loopId : Int,
                       var runResult : RunResult,
                       var count : Int,
                       val allWarnings :  mutable.HashSet[Warning]) extends LoopResult {

  var dominatorTree : Option[DominatorTree] = None;
  
  override def foreach(f: EventForReportElement => Unit): Unit = {
    runResult.foreach(f)
  }


  override def isFailure: Boolean = runResult.isFailure;

  override def dataRaceCount: Int = runResult.dataRaceCount

  override def warningIdList: Set[Warning] = allWarnings.toSet;

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
     
    }
    allWarnings.addAll(newRunResult.warningIdList);
  }

  override def setDominatorTree(tree: DominatorTree): Unit = {
    dominatorTree = Some(tree)
  }


}

object LoopResultSingle {
  
  def apply(loopId : Int, runResult : RunResult, count : Int): LoopResultSingle = {
    val allWarnings = new  mutable.HashSet[Warning]();
    allWarnings.addAll(runResult.warningIdList);
    new LoopResultSingle(loopId,runResult,count,allWarnings);
  }
  
}
