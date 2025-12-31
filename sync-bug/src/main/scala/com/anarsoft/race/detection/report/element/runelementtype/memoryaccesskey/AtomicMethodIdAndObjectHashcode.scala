package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}


class AtomicMethodIdAndObjectHashcode(val atomicMethodId: Int, val objectHashCode: Long)  
  extends GenericMemoryAccessKey[AtomicMethodIdAndObjectHashcode] {
  override def asString(context: DescriptionContext): String = context.methodNameWithoutSource(atomicMethodId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(atomicMethodId)
  }

  override def objectHashCodeOption: Option[Long] = Some(objectHashCode);


  private def canEqual(other: Any): Boolean = other.isInstanceOf[AtomicMethodIdAndObjectHashcode]
  
  override def equals(other: Any): Boolean = other match {
    case that: AtomicMethodIdAndObjectHashcode =>
      that.canEqual(this) &&
        atomicMethodId == that.atomicMethodId &&
        objectHashCode == that.objectHashCode
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(atomicMethodId, objectHashCode)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def compare(that: AtomicMethodIdAndObjectHashcode): Int = {
    if(objectHashCode != that.objectHashCode) {
      objectHashCode.compare(that.objectHashCode)
    } else {
      atomicMethodId.compare(that.atomicMethodId)
    }
  }
}
