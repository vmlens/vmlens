package com.anarsoft.race.detection.createpartialorder

import com.anarsoft.race.detection.stacktrace.StacktraceNode
import com.vmlens.report.element.OperationTextFactory

class SyncActionEventMonitorGuineaPig(val isEnter: Boolean, val runPosition: Int,
                                      val threadIndex: Int) extends SyncActionEventWithCompareType[SyncActionEventMonitorGuineaPig] {

  override def compareType(other: SyncActionEventMonitorGuineaPig): Int = 0

  override def loopId: Int = 0

  override def runId: Int = 0

  override def stacktraceNode: Option[StacktraceNode] = None

  override def operationTextFactory: OperationTextFactory = null

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
