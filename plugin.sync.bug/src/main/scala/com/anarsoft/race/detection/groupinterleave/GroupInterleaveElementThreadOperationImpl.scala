package com.anarsoft.race.detection.groupinterleave


import com.anarsoft.race.detection.createpartialorderthreadoperation.{AddToPartialOrderBuilder, ThreadOperation}
import com.anarsoft.race.detection.partialorder.PartialOrderBuilder
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.{SetStacktraceNodeInEvent, WithSetStacktraceNode}
import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.util.EventArray

class GroupInterleaveElementThreadOperationImpl[EVENT <: ThreadOperation with WithSetStacktraceNode
  with EventForReportElement]
(val eventArray: EventArray[EVENT])
  extends GroupInterleaveElement {

  def setStacktraceNode(threadIdToStacktraceNodeArray: Map[Long, Array[StacktraceNode]]): Unit = {
    new SetStacktraceNodeInEvent().process(eventArray, threadIdToStacktraceNodeArray);
  }

  def addToPartialOrderBuilder(partialOrderBuilder: PartialOrderBuilder): Unit = {
    new AddToPartialOrderBuilder().process(eventArray, partialOrderBuilder);
  }

  def foreach(f: EventForReportElement => Unit): Unit = {
    eventArray.foreach(f);
  }
}