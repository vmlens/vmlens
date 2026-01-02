package com.anarsoft.race.detection.report.element.runelementtype.memoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey.GenericDominatorMemoryAccessKey


class AtomicMethodIdAndObjectHashcode(val atomicMethodId: Int, val objectHashCode: Long)  
  extends  MemoryAccessKey  {
 
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

 
}
