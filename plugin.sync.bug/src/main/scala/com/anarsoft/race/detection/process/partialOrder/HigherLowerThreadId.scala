package com.anarsoft.race.detection.process.partialOrder

class HigherLowerThreadId(val higherId : Long , val lowerId : Long) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.process.partialOrder.HigherLowerThreadId]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.process.partialOrder.HigherLowerThreadId => that.canEqual(HigherLowerThreadId.this) && higherId == that.higherId && lowerId == that.lowerId
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + higherId.hashCode) + lowerId.hashCode
  }
}

object HigherLowerThreadId
{
  
  def apply(a : Long , b : Long) =
  {
    if( a > b )
    {
      new HigherLowerThreadId(a,b)
    }
    else
    {
      new  HigherLowerThreadId(b,a)
    }
  }
  
  
  
}