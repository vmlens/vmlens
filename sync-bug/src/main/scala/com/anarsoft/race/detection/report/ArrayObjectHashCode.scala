package com.anarsoft.race.detection.report

class ArrayObjectHashCode(val objectHashCode: Long) extends StaticMemoryAccessId {


  private def canEqual(other: Any): Boolean = other.isInstanceOf[ArrayObjectHashCode]
  
  override def equals(other: Any): Boolean = other match {
    case that: ArrayObjectHashCode =>
      that.canEqual(this) &&
        objectHashCode == that.objectHashCode
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(objectHashCode)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
