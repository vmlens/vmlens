package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContext, WithPositionImpl}
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.report.element.runelementtype.{ReportOperation, ReportThreadOperation}
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationThread
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex


trait ThreadJoinedEvent extends LoadedInterleaveActionEvent  with ThreadOperation
  with EventForReportElement
  with WithSetStacktraceNode {
  
  def joinedThreadIndex : Int;
  def eventType  : Int;

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.threadJoinedEvents.add(this);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    partialOrderBuilder.addLeftBeforeRight( new WithPositionImpl(partialOrderBuilder.lastPosition(joinedThreadIndex),
      joinedThreadIndex),this)
  }

  override def runElementType: ReportOperation = {
    val threadType = EventTypeThread.fromCode(eventType);
    new OperationThread( threadType, ReportThreadOperation.JOIN , new LoopRunAndThreadIndex(loopId, runId, joinedThreadIndex));
  }
  
}
