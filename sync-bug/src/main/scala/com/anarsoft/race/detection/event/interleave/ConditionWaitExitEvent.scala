package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromCategory
import com.vmlens.report.input.run.{LockAccess, LockOperation, RunElementType}

trait ConditionWaitExitEvent extends LockEvent with WithLockEnterEvent with LockTypeClassFromCategory[WithLockEnterEvent]  {

  override def runElementType : RunElementType =
    new LockAccess(LockOperation.CONDITION_WAIT_EXIT, lockTypeClass().reportLockType(), objectHashCode);

}
