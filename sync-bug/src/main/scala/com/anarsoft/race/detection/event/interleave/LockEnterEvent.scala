package com.anarsoft.race.detection.event.interleave

import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType}

trait LockEnterEvent extends LockEvent with WithLockEnterEvent  {

  override def runElementType : RunElementType =  
    new LockAccess(LockOperation.LOCK_ENTER, lockTypeClass().reportLockType(), objectHashCode);
  
  
}
