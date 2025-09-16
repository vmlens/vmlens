package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialorderthreadoperation.ThreadOperation
import com.anarsoft.race.detection.partialorder.{BuildPartialOrderContext, WithPositionImpl}
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.vmlens.report.element.LoopRunAndThreadIndex
import com.vmlens.report.runelementtype.{RunElementType, UIThreadOperation, ThreadRunElementType}
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread

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

  override def runElementType: RunElementType = {
    val threadType = EventTypeThread.fromCode(eventType);
    new ThreadRunElementType( threadType, UIThreadOperation.JOIN , new LoopRunAndThreadIndex(loopId, runId, joinedThreadIndex));
  }
  
}
