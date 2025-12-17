package com.anarsoft.race.detection.report.element

class LoopRunAndThreadIndex(val loopId: Int,  val runId: Int,  val threadIndex: Int) {

  override def toString: String = "LoopRunAndThreadIndex{" + "loopId=" + loopId + ", runId=" + runId + ", threadIndex=" + threadIndex + '}'

  private def canEqual(other: Any): Boolean = other.isInstanceOf[LoopRunAndThreadIndex]

  override def equals(other: Any): Boolean = other match {
    case that: LoopRunAndThreadIndex =>
      that.canEqual(this) &&
        loopId == that.loopId &&
        runId == that.runId &&
        threadIndex == that.threadIndex
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(loopId, runId, threadIndex)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
