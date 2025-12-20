package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


class FieldIdAndObjectHashcode(val fieldId: Int,val objectHashCode: Long) extends MemoryAccessKey {
  override def asString(context: DescriptionContext): String = context.fieldName(fieldId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsField(fieldId)
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);


  private def canEqual(other: Any): Boolean = other.isInstanceOf[FieldIdAndObjectHashcode]
  
  override def equals(other: Any): Boolean = other match {
    case that: FieldIdAndObjectHashcode =>
      that.canEqual(this) &&
        fieldId == that.fieldId &&
        objectHashCode == that.objectHashCode
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(fieldId, objectHashCode)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
