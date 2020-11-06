package com.anarsoft.race.detection.process.interleave.loopAlgo

import java.util.ArrayList
import com.anarsoft.race.detection.process.interleave._;


abstract class IssueType {
   def loopResult(currentList: ArrayList[InterleaveEventStatement], count : Int) : LoopResult;
}

case class Deadlock() extends IssueType
{
   def loopResult(currentList: ArrayList[InterleaveEventStatement], count : Int) = LoopResultDeadlock(currentList, count)
}

case class RaceCondition(val hasRead : Boolean) extends IssueType
{
   def loopResult(currentList: ArrayList[InterleaveEventStatement], count : Int) = LoopResultRace(currentList, hasRead, count)
}