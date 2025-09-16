package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContext, WithPositionImpl}
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.vmlens.report.element.LoopRunAndThreadIndex
import com.vmlens.report.runelementtype.{RunElementType, UIThreadOperation, ThreadRunElementType}
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread


trait ThreadStartEvent extends LoadedInterleaveActionEvent
  with ThreadOperation
  with EventForReportElement
  with WithSetStacktraceNode {

  def startedThreadIndex: Int;
  def eventType: Int;

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.threadStartEvents.add(this);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    partialOrderBuilder.addLeftBeforeRight(this, new WithPositionImpl(0, startedThreadIndex))
  }

  override def runElementType: RunElementType = {
    val threadType = EventTypeThread.fromCode(eventType);
    new ThreadRunElementType(threadType, UIThreadOperation.START , new LoopRunAndThreadIndex(loopId, runId, startedThreadIndex));
  }
}
