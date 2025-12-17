package com.anarsoft.race.detection.report.element

class StacktraceLeaf(val stacktraceElements: List[StacktraceElement]) {


  private def canEqual(other: Any): Boolean = other.isInstanceOf[StacktraceLeaf]
  
  override def equals(other: Any): Boolean = other match {
    case that: StacktraceLeaf =>
      that.canEqual(this) &&
        stacktraceElements == that.stacktraceElements
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(stacktraceElements)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
