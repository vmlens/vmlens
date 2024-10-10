package com.anarsoft.race.detection.createstacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode

class StacktraceNodeRoot(val methodId: Int) extends StacktraceNode {

  override def getParent: Option[StacktraceNode] = None;
  
  override def equals(other: Any): Boolean = other match {
    case that: StacktraceNodeRoot =>
      (that canEqual this) &&
        methodId == that.methodId
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[StacktraceNodeRoot]

  override def hashCode(): Int = {
    val state = Seq(methodId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
