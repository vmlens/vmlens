package com.anarsoft.race.detection.report.element.runelementtype

import com.anarsoft.race.detection.report.description.{DescriptionContext, NeedsDescriptionCallback}
import com.anarsoft.race.detection.report.element.runelementtype.objecthashcodemap.ObjectHashCodeMap


class MethodWithLockAccess(val atomicMethodId: Int,val objectHashCode: Long, val lockOperation: LockOperation,val lockType: ReportLockType) extends ReportOperation {
  private var objectHashCodeMap: ObjectHashCodeMap = null

  override def operation: String = lockType.textForMethodWithLock + " " + lockOperation.text

  override def element(context: DescriptionContext): String = context.methodName(atomicMethodId)

  override def addToNeedsDescription(callback: NeedsDescriptionCallback): Unit = {
    callback.needsMethod(atomicMethodId)
  }

  override def objectString(context: DescriptionContext): String = objectHashCodeMap.asUiString(objectHashCode)

  override def setObjectHashCodeMap(objectHashCodeMap: ObjectHashCodeMap, threadIndex: Int): Unit = {
    this.objectHashCodeMap = objectHashCodeMap
    objectHashCodeMap.add(objectHashCode, threadIndex)
  }
}
