package com.anarsoft.race.detection.process.aggregate

class SameAggregateId(val lower : Int, val higher : Int  ) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.process.aggregate.SameAggregateId]
  }

  override def equals(other: Any) = {
    other match {
      case that:com.anarsoft.race.detection.process.aggregate.SameAggregateId => that.canEqual(SameAggregateId.this) && lower == that.lower && higher == that.higher
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + lower.hashCode) + higher.hashCode
  }
}

object SameAggregateId
{
  def apply(first : Int , second : Int) =
  {
    if( first < second )
    {
      new SameAggregateId(first,second);
    }
    else
    {
       new SameAggregateId(second,first);
    }
      
    
    
    
    
  }
  
  
  
}