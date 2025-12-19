package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


class AtomicMethodIdAndObjectHashcode(val atomicMethodId: Int, val objectHashCode: Long) extends MemoryAccessKey {
  override def asString(context: DescriptionContext): String = context.methodNameWithoutSource(atomicMethodId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(atomicMethodId)
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);


}
