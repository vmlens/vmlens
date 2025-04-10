package com.anarsoft.race.detection.event.interleave

import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType, MethodWithLockAccess}

trait AtomicReadWriteLockEnterEvent extends AtomicReadWriteLockEvent with WithLockEnterEvent {

  def  atomicMethodId  : Int;

  override def runElementType: RunElementType =
    new MethodWithLockAccess(atomicMethodId,objectHashCode,LockOperation.LOCK_ENTER, lockTypeClass().reportLockType());


}
