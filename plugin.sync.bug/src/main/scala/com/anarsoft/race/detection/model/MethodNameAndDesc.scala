package com.anarsoft.race.detection.model

class MethodNameAndDesc(val name : String, val desc : String) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.anarsoft.race.detection.model.MethodNameAndDesc]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.anarsoft.race.detection.model.MethodNameAndDesc => that.canEqual(MethodNameAndDesc.this) && name == that.name && desc == that.desc
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime * (prime + name.hashCode) + desc.hashCode
  }
  
  
  override def toString() = name + " " + desc;
  
  
}