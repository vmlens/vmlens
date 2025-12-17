package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback};

class ArrayObjectHashCodeAndIndex( val objectHashCode: Long, val arrayIndex: Int) extends MemoryAccessKey {
  
  override def asString(context: DescriptionContext): String = " array[" + arrayIndex + "]"

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);
}
