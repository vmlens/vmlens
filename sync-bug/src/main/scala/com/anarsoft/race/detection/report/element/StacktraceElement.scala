package com.anarsoft.race.detection.report.element

class StacktraceElement(val methodId: Int) {


  private def canEqual(other: Any): Boolean = other.isInstanceOf[StacktraceElement]
  
  override def equals(other: Any): Boolean = other match {
    case that: StacktraceElement =>
      that.canEqual(this) &&
        methodId == that.methodId
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(methodId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
