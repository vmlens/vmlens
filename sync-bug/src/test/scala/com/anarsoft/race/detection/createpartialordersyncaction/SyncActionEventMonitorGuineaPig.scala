package com.anarsoft.race.detection.createpartialordersyncaction

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.anarsoft.race.detection.report.element.runelementtype.ReportOperation

class SyncActionEventMonitorGuineaPig(val isEnter: Boolean, val runPosition: Int,
                                      val threadIndex: Int) extends SyncActionEventWithCompareType[SyncActionEventMonitorGuineaPig] {

  override def compareType(other: SyncActionEventMonitorGuineaPig): Int = 0

  override def loopId: Int = 0

  override def runId: Int = 0

  override def methodId: Int = 0

  override def stacktraceNode: Option[StacktraceNode] = None

  override def runElementType: ReportOperation = null

  override def equals(other: Any): Boolean = other match {
    case that: SyncActionEventMonitorGuineaPig =>
      (that canEqual this) &&
        isEnter == that.isEnter &&
        runPosition == that.runPosition &&
        threadIndex == that.threadIndex
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[SyncActionEventMonitorGuineaPig]

  override def hashCode(): Int = {
    val state = Seq(isEnter, runPosition, threadIndex)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
