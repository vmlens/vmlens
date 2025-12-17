package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


class FieldIdAndObjectHashcode(val fieldId: Int,val objectHashCode: Long) extends MemoryAccessKey {
  override def asString(context: DescriptionContext): String = context.fieldName(fieldId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsField(fieldId)
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);
  
}
