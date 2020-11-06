package com.anarsoft.race.detection.process.interleave.loopAlgo

import com.anarsoft.race.detection.process.interleave._;
import java.util.ArrayList
import com.vmlens.trace.agent.bootstrap.util.LoopResultStatusCodes

sealed abstract class TakeFrom {
 
  def runId : Int;
  def loopResult(currentList: ArrayList[InterleaveEventStatement]) : LoopResult;
  
}

case class TakeSuccess(val runId : Int ,  val loopInfo : LoopInfo) extends TakeFrom{
   def loopResult(currentList: ArrayList[InterleaveEventStatement] )  =   LoopEndedNormally(currentList, loopInfo.runMap.list.size(), loopInfo.statusFromLoopEnd  , loopInfo.hasWarning)
}

case class TakeIssue(val runId : Int , val issueType :  IssueType, val loopInfo : LoopInfo) extends TakeFrom
{
    def loopResult(currentList: ArrayList[InterleaveEventStatement])  =  issueType.loopResult(currentList  , loopInfo.runMap.list.size()); //  
}


case class TakeWithError(val runId : Int, val loopInfo : LoopInfo) extends TakeFrom
{
    def loopResult(currentList: ArrayList[InterleaveEventStatement])  = LoopResultError(currentList,  loopInfo.runMap.list.size());
}