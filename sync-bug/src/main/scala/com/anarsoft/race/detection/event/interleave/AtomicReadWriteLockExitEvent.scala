package com.anarsoft.race.detection.event.interleave


import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType, MethodWithLockAccess}


trait AtomicReadWriteLockExitEvent  extends AtomicReadWriteLockEvent with WithLockExitEvent {

  def atomicMethodId: Int;

  override def runElementType: RunElementType =
    new MethodWithLockAccess(atomicMethodId,objectHashCode, LockOperation.LOCK_EXIT, lockTypeClass().reportLockType());


}
