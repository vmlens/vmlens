package com.anarsoft.race.detection.createpartialorder

class SyncActionEventMonitorGuineaPig(val isEnter: Boolean, val runPosition: Int,
                                      val threadIndex: Int) extends SyncActionEvent[SyncActionEventMonitorGuineaPig] {

  override def compareType(other: SyncActionEventMonitorGuineaPig): Int = 0

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
