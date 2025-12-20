package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


class FieldId(val fieldId: Int) extends MemoryAccessKey {
  override def asString(context: DescriptionContext): String = context.fieldName(fieldId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsField(fieldId)
  }

  override def objectHashCodeOption: Option[Long] = None


  private def canEqual(other: Any): Boolean = other.isInstanceOf[FieldId]
  
  override def equals(other: Any): Boolean = other match {
    case that: FieldId =>
      that.canEqual(this) &&
        fieldId == that.fieldId
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(fieldId)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
