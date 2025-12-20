package com.anarsoft.race.detection.report.element.runelementtype.operation

import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}
import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap


class OperationLockAccess(val monitorOperation: LockOperation, val lockType: ReportLockType, val objectHashCode: Long) extends ReportOperation {
  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = lockType.text + " " + monitorOperation.text

  override def element(context: DescriptionContext) = ""

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
  }

  override def objectString(context: DescriptionContext): String = objectHashCodeMap.asUiString(objectHashCode)

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
    this.objectHashCodeMap = objectHashCodeMap
    objectHashCodeMap.add(objectHashCode, threadIndex)
  }

  override def take(): Boolean = true;

  override def isDataRace: Boolean = false;
  
  private def canEqual(other: Any): Boolean = other.isInstanceOf[OperationLockAccess]
  
  override def equals(other: Any): Boolean = other match {
    case that: OperationLockAccess =>
      that.canEqual(this) &&
        monitorOperation == that.monitorOperation &&
        lockType == that.lockType &&
        objectHashCode == that.objectHashCode
    case _ => false
  }
  
  override def hashCode(): Int = {
    val state = Seq(monitorOperation, lockType, objectHashCode)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
