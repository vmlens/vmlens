package com.anarsoft.race.detection.groupinterleave

import com.anarsoft.race.detection.createlastthreadposition.{CreateLastThreadPosition, LastThreadPositionMap}
import com.anarsoft.race.detection.createpartialorderthreadoperation.{AddToPartialOrderBuilder, ThreadOperation}
import com.anarsoft.race.detection.event.interleave.{LockEnterEvent, LockEvent, LockExitEvent}
import com.anarsoft.race.detection.partialorder.BuildPartialOrderContext
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementThreadOperationImpl[EVENT <: ThreadOperation with WithSetStacktraceNode
  with EventForReportElement]
    (val eventArray: EventArray[EVENT])
  extends GroupInterleaveElement {

  override def addLastThreadPosition(lastThreadPositionMap: LastThreadPositionMap): Unit = {
    new CreateLastThreadPosition().process(eventArray, lastThreadPositionMap);
  }

  override def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Int, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  override def addToPartialOrderBuilder(partialOrderBuilder: BuildPartialOrderContext): Unit = {
    new AddToPartialOrderBuilder().process(eventArray, partialOrderBuilder);
  }

  override def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }
  
}