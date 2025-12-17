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
}
