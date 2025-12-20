package com.anarsoft.race.detection.report.element

import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation


class RunElement(val loopRunAndThreadIndex: LoopRunAndThreadIndex, 
                 val runPosition: Int, 
                 val stacktraceLeaf: StacktraceLeaf,
                 val operationTextFactory: ReportOperation,
                 val inMethodId: Int) extends  Ordered[RunElement] {


  override def compare(that: RunElement): Int = {
    if(loopRunAndThreadIndex.runId != that.loopRunAndThreadIndex.runId) {
      loopRunAndThreadIndex.runId.compare(that.loopRunAndThreadIndex.runId)
    } else {
      runPosition.compare(that.runPosition)
    }
  }


  private def canEqual(other: Any): Boolean = other.isInstanceOf[RunElement]
  
  override def equals(other: Any): Boolean = other match {
    case that: RunElement =>
      that.canEqual(this) &&
        loopRunAndThreadIndex == that.loopRunAndThreadIndex &&
        stacktraceLeaf == that.stacktraceLeaf &&
        operationTextFactory == that.operationTextFactory &&
        inMethodId == that.inMethodId
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(loopRunAndThreadIndex, stacktraceLeaf, operationTextFactory, inMethodId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
