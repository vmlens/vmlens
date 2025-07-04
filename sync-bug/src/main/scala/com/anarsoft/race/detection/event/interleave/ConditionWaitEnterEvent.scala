package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromCategory
import com.anarsoft.race.detection.event.interleave.locktype.LockType
import com.vmlens.report.runelementtype.{LockAccess, LockOperation, RunElementType}

trait ConditionWaitEnterEvent  extends LockEvent with WithLockExitEvent with LockTypeClassFromCategory[WithLockExitEvent] {

  override def runElementType: RunElementType = {
    new LockAccess(LockOperation.CONDITION_WAIT_ENTER, lockTypeClass().reportLockType(), objectHashCode)
  }



}
