package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContext, WithPositionImpl}
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.vmlens.report.element.LoopRunAndThreadIndex
import com.vmlens.report.runelementtype.{RunElementType, ThreadRunElementType}


trait ThreadStartEvent extends LoadedInterleaveActionEvent
  with ThreadOperation
  with EventForReportElement
  with WithSetStacktraceNode {

  def startedThreadIndex: Int;

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.threadStartEvents.add(this);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    partialOrderBuilder.addLeftBeforeRight(this, new WithPositionImpl(0, startedThreadIndex))
  }

  override def runElementType: RunElementType = {
    new ThreadRunElementType("start (%s)", new LoopRunAndThreadIndex(loopId, runId, startedThreadIndex));
  }
}
