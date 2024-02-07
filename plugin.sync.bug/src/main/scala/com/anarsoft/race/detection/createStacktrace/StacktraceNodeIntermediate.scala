package com.anarsoft.race.detection.createStacktrace

import com.anarsoft.race.detection.stacktrace.StacktraceNode

class StacktraceNodeIntermediate(val methodId: Int, val parent: StacktraceNode) extends StacktraceNode {


  override def equals(other: Any): Boolean = other match {
    case that: StacktraceNodeIntermediate =>
      (that canEqual this) &&
        methodId == that.methodId &&
        parent == that.parent
    case _ => false
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[StacktraceNodeIntermediate]

  override def hashCode(): Int = {
    val state = Seq(methodId, parent)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
