package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType}

trait LockExitEvent extends LockEvent with WithLockExitEvent  with LockTypeClassFromId[WithLockExitEvent] {

  override def runElementType: RunElementType =
    new LockAccess(LockOperation.LOCK_EXIT, lockTypeClass().reportLockType(), objectHashCode);
  
}
