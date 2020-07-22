package com.anarsoft.race.detection.process.method

class MethodIdAndThreadId(val methodId : Int, val threadId : Long) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.process.method.MethodIdAndThreadId]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.process.method.MethodIdAndThreadId => that.canEqual(MethodIdAndThreadId.this) && methodId == that.methodId && threadId == that.threadId
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + methodId.hashCode) + threadId.hashCode
  }
}