package com.anarsoft.race.detection.event.interleave


import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.vmlens.report.input.run.{LockAccess, LockOperation, MethodWithLockAccess, ReportLockType, RunElementType}


trait AtomicReadWriteLockExitEvent  extends AtomicReadWriteLockEvent with WithLockExitEvent with LockTypeClassFromId[WithLockExitEvent] {

  def atomicMethodId: Int;

  override def runElementType: RunElementType =
    new MethodWithLockAccess(atomicMethodId,objectHashCode, LockOperation.LOCK_EXIT, lockTypeClass().reportLockType());


}
