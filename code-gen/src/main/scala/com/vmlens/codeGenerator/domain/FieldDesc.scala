package com.vmlens.codeGenerator.domain

class FieldDesc(val name : String, val typ : FieldTyp) extends Equals {
  def canEqual(other: Any) = {
    other.isInstanceOf[com.vmlens.codeGenerator.domain.FieldDesc]
  }

  override def equals(other: Any) = {
    other match {
      case that: com.vmlens.codeGenerator.domain.FieldDesc => that.canEqual(FieldDesc.this) && name == that.name
      case _ => false
    }
  }

  override def hashCode() = {
    val prime = 41
    prime + name.hashCode
  }
}