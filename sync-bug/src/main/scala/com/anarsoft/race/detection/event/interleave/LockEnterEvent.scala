package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.vmlens.report.input.run.{LockAccess, LockOperation, ReportLockType, RunElementType}

trait LockEnterEvent extends LockEvent with WithLockEnterEvent  with LockTypeClassFromId[WithLockEnterEvent] {

  override def runElementType : RunElementType =  
    new LockAccess(LockOperation.LOCK_ENTER, lockTypeClass().reportLockType(), objectHashCode);
  
  
}
