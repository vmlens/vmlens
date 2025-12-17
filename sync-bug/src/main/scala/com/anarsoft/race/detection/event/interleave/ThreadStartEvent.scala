package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContext, WithPositionImpl}
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.report.element.runelementtype.{ReportOperation, ReportThreadOperation}
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationThread
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex


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

  override def runElementType: ReportOperation = {
    val threadType = EventTypeThread.fromCode(eventType);
    new OperationThread(threadType, ReportThreadOperation.START , new LoopRunAndThreadIndex(loopId, runId, startedThreadIndex));
  }
}
