package com.anarsoft.race.detection.loopresult

import com.anarsoft.race.detection.dominatortree.DominatorTree
import com.anarsoft.race.detection.rundata.RunResultImpl

import scala.collection.mutable

class LoopResultCollection(val useList : Boolean) {

  private val loopIdToResult = new mutable.HashMap[Int, LoopResult]

  def put(runResult: RunResultImpl, dominatorTree : Option[DominatorTree]): Unit = {
    val loopId = runResult.loopId

    loopIdToResult.get(loopId) match {
      case Some(x) => {
        x.add(runResult)
        dominatorTree.foreach( dom =>  x.setDominatorTree(dom) )
        
      }
      case None => {
        if(useList) {
          val loopResultList = new LoopResultList(loopId);
          loopResultList.add(runResult)
          loopIdToResult.put(loopId, loopResultList)
          dominatorTree.foreach(dom => loopResultList.setDominatorTree(dom))
        } else {
          val single = LoopResultSingle(loopId, runResult, runResult.runId);
          loopIdToResult.put(loopId,  single)
          dominatorTree.foreach(dom => single.setDominatorTree(dom))
        }
      }
    }
  }

  def build(): Seq[LoopResult] = loopIdToResult.values.toSeq;
  
  

}
