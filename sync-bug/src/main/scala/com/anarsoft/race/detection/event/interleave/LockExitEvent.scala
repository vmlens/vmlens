package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess


trait LockExitEvent extends LockEvent with WithLockExitEvent  with LockTypeClassFromId[WithLockExitEvent] {

  override def runElementType: ReportOperation =
    new OperationLockAccess(LockOperation.LOCK_EXIT, lockTypeClass().reportLockType(), objectHashCode);
  
}
