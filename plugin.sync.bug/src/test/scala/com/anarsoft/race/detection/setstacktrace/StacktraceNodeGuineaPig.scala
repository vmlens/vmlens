package com.anarsoft.race.detection.setstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode

class StacktraceNodeGuineaPig(val forMethodCounter: Int) extends StacktraceNode {

  def getParent: Option[StacktraceNode] = None;

  def methodId: Int = 0;
  

  override def equals(other: Any): Boolean = other match {
    case that: StacktraceNodeGuineaPig =>
      (that canEqual this) &&
        forMethodCounter == that.forMethodCounter
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[StacktraceNodeGuineaPig]

  override def hashCode(): Int = {
    val state = Seq(forMethodCounter)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
