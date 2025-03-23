package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createlastthreadposition.{CreateLastThreadPosition, LastThreadPositionMap}
import com.anarsoft.race.detection.createpartialordersyncaction.{AddToPartialOrderBuilder, SyncActionEventWithCompareType}
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.sortutil.EventContainer
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray
import com.vmlens.report.assertion.EventWithAssertion

class GroupInterleaveElementSyncActionImpl[EVENT <: SyncActionEventWithCompareType[EVENT]
  with WithSetStacktraceNode with EventWithAssertion]
(val eventArray: EventArray[EVENT], val createContainer: (EVENT) => EventContainer[EVENT])
  extends GroupInterleaveElement {

  def addLastThreadPosition(lastThreadPositionMap: LastThreadPositionMap): Unit = {
    new CreateLastThreadPosition().process(eventArray, lastThreadPositionMap);
  }
  
  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    new AddToPartialOrderBuilder(createContainer).process(eventArray, partialOrderBuilder);
  }

  def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }
}
