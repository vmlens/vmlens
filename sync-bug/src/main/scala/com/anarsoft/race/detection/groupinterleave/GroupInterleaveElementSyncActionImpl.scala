package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createlastthreadposition.{CreateLastThreadPosition, LastThreadPositionMap}
import com.anarsoft.race.detection.createpartialordersyncaction.{AddToPartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.event.interleave.{LockEnterEvent, LockExitEvent, VolatileFieldAccessEvent}
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.assertion.{EventForAssertion, OnEvent, TypeLockEnter, TypeLockExit, TypeVolatileFieldAccess}

class GroupInterleaveElementSyncActionImpl[EVENT <: SyncActionEventWithCompareType[EVENT]
  with WithSetStacktraceNode]
    (val eventArray: EventArray[EVENT], val createContainer: (EVENT) => EventContainer[EVENT])
  extends GroupInterleaveElement {

  override def addLastThreadPosition(lastThreadPositionMap: LastThreadPositionMap): Unit = {
    new CreateLastThreadPosition().process(eventArray, lastThreadPositionMap);
  }

  override def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  override def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    new AddToPartialOrderBuilder(createContainer).process(eventArray, partialOrderBuilder);
  }

  override def addToOnEvent(onEvent: OnEvent): Unit = {
    for (event <- eventArray) {
      event match {
        case lockEnterEvent: LockEnterEvent => {
          val eventForAssertion = new EventForAssertion(
            event.runId,
            event.loopId,
            event.runPosition,
            new TypeLockEnter());
          onEvent.onEvent(eventForAssertion)
        }
        case lockExitEvent: LockExitEvent => {
          val eventForAssertion = new EventForAssertion(
            event.runId,
            event.loopId,
            event.runPosition,
            new TypeLockExit());
          onEvent.onEvent(eventForAssertion)
        }
        case volatileFieldAccessEvent: VolatileFieldAccessEvent => {
          val eventForAssertion = new EventForAssertion(
            event.runId,
            event.loopId,
            event.runPosition,
            new TypeVolatileFieldAccess());
          onEvent.onEvent(eventForAssertion)
        }
        case _ =>
      }
    }
  }

  override def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }
}
