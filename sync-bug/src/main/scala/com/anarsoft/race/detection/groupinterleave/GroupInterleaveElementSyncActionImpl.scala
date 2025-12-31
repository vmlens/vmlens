package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createlastthreadposition.{CreateLastThreadPosition, LastThreadPositionMap}
import com.anarsoft.race.detection.createpartialordersyncaction.{AddToPartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.event.interleave.{LockEnterEvent, LockExitEvent, VolatileFieldAccessEvent}
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

abstract class GroupInterleaveElementSyncActionImpl[EVENT <: SyncActionEventWithCompareType[EVENT]
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
  
  override def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }
}
