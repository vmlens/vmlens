package com.anarsoft.race.detection.report.element.runelementtype.dominatormemoryaccesskey

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}

/**
 * 
 * This class represents the complete atomic object
 * so the atomicMethodId is only used for getting the name of the class but not for equals
 * or comparison
 * 
 * @param atomicMethodId
 * @param objectHashCode
 */

class AtomicNonBlockingKey(val atomicMethodId: Int, val objectHashCode: Long)  
  extends GenericDominatorMemoryAccessKey[AtomicNonBlockingKey] {
  
  override def asString(context: DescriptionContext): String = 
    context.classNameForMethodId(atomicMethodId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = 
    callback.needsMethod(atomicMethodId)



  override def compare(that: AtomicNonBlockingKey): Int = {
    objectHashCode.compare(that.objectHashCode)
  }


  private def canEqual(other: Any): Boolean = other.isInstanceOf[AtomicNonBlockingKey]
  
  override def equals(other: Any): Boolean = other match {
    case that: AtomicNonBlockingKey =>
      that.canEqual(this) &&
        objectHashCode == that.objectHashCode
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(objectHashCode)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
