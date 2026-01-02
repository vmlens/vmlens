package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromCategory
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess


trait ConditionWaitExitEvent extends LockEvent with WithLockEnterEvent with LockTypeClassFromCategory[WithLockEnterEvent]  {

  override def runElementType : ReportOperation =
    new OperationLockAccess(LockOperation.CONDITION_WAIT_EXIT, lockTypeClass().reportLockType(), objectHashCode);

}
