package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}


class OperationMethodWithLockAccess(val atomicMethodId: Int, val objectHashCode: Long, val lockOperation: LockOperation, val lockType: ReportLockType) extends ReportOperation {
  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = lockType.textForMethodWithLock + " " + lockOperation.text

  override def element(context: DescriptionContext): String = context.methodNameWithoutSource(atomicMethodId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(atomicMethodId)
  }

  override def objectString(context: DescriptionContext): String = objectHashCodeMap.asUiString(objectHashCode)

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
    this.objectHashCodeMap = objectHashCodeMap
    objectHashCodeMap.add(objectHashCode, threadIndex)
  }

  override def take(): Boolean = ! objectHashCodeMap.isSingleThreaded(objectHashCode)

  override def isDataRace: Boolean = false;
  
  private def canEqual(other: Any): Boolean = other.isInstanceOf[OperationMethodWithLockAccess]
  
  override def equals(other: Any): Boolean = other match {
    case that: OperationMethodWithLockAccess =>
      that.canEqual(this) &&
        atomicMethodId == that.atomicMethodId &&
        objectHashCode == that.objectHashCode &&
        lockOperation == that.lockOperation &&
        lockType == that.lockType
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(atomicMethodId, objectHashCode, lockOperation, lockType)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
