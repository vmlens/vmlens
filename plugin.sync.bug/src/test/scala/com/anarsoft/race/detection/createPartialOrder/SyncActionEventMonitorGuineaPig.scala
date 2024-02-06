package com.anarsoft.race.detection.createPartialOrder

class SyncActionEventMonitorGuineaPig(val isEnter: Boolean, val positionInRun: Int,
                                      val threadId: Long) extends SyncActionEvent[SyncActionEventMonitorGuineaPig] {

  override def compareType(other: SyncActionEventMonitorGuineaPig): Int = 0

  override def equals(other: Any): Boolean = other match {
    case that: SyncActionEventMonitorGuineaPig =>
      (that canEqual this) &&
        isEnter == that.isEnter &&
        positionInRun == that.positionInRun &&
        threadId == that.threadId
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[SyncActionEventMonitorGuineaPig]

  override def hashCode(): Int = {
    val state = Seq(isEnter, positionInRun, threadId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
