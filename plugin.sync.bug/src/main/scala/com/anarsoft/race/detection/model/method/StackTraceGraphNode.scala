package com.anarsoft.race.detection.model.method




class StackTraceGraphNode(val methodId : Int, val parentOrdinal : Int) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.method.StackTraceGraphNode]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.model.method.StackTraceGraphNode => that.canEqual(StackTraceGraphNode.this) && methodId == that.methodId && parentOrdinal == that.parentOrdinal
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + methodId.hashCode) + parentOrdinal.hashCode
  }
}