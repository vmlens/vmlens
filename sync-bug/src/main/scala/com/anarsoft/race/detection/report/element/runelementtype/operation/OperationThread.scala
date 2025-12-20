package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.element.runelementtype.{ReportOperation, ReportThreadOperation}
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread
import com.anarsoft.race.detection.report.element.LoopRunAndThreadIndex
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap

class OperationThread(val eventTypeThread: EventTypeThread, val threadOperation: ReportThreadOperation,
                      val onThreadIndex: LoopRunAndThreadIndex) extends ReportOperation {
  override def operation: String = eventTypeThread.text + " " + threadOperation.text

  override def element(context: DescriptionContext) = ""

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
  }

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
  }

  override def objectString(context: DescriptionContext): String = context.threadName(onThreadIndex)

  def take(): Boolean = true;

  override def isDataRace: Boolean = false;

  private def canEqual(other: Any): Boolean = other.isInstanceOf[OperationThread]
  
  override def equals(other: Any): Boolean = other match {
    case that: OperationThread =>
      that.canEqual(this) &&
        eventTypeThread == that.eventTypeThread &&
        threadOperation == that.threadOperation &&
        onThreadIndex == that.onThreadIndex
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(eventTypeThread, threadOperation, onThreadIndex)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
