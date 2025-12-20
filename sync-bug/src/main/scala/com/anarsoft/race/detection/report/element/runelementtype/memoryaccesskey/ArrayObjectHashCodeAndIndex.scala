package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback};

class ArrayObjectHashCodeAndIndex( val objectHashCode: Long, val arrayIndex: Int) extends MemoryAccessKey {
  
  override def asString(context: DescriptionContext): String = " array[" + arrayIndex + "]"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);


  private def canEqual(other: Any): Boolean = other.isInstanceOf[ArrayObjectHashCodeAndIndex]
  
  override def equals(other: Any): Boolean = other match {
    case that: ArrayObjectHashCodeAndIndex =>
      that.canEqual(this) &&
        objectHashCode == that.objectHashCode &&
        arrayIndex == that.arrayIndex
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(objectHashCode, arrayIndex)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
