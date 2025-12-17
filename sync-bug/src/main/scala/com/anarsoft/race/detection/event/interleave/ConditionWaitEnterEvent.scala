package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromCategory
import com.anarsoft.race.detection.event.interleave.locktype.LockType
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess


trait ConditionWaitEnterEvent  extends LockEvent with WithLockExitEvent with LockTypeClassFromCategory[WithLockExitEvent] {

  override def runElementType: ReportOperation = {
    new OperationLockAccess(LockOperation.CONDITION_WAIT_ENTER, lockTypeClass().reportLockType(), objectHashCode)
  }



}
