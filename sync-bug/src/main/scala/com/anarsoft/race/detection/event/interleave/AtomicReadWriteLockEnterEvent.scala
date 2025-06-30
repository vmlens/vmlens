package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.vmlens.report.runelementtype.{LockAccess, LockOperation, MethodWithLockAccess, ReportLockType, RunElementType}

trait AtomicReadWriteLockEnterEvent extends AtomicReadWriteLockEvent with WithLockEnterEvent with LockTypeClassFromId[WithLockEnterEvent] {

  def  atomicMethodId  : Int;

  override def runElementType: RunElementType =
    new MethodWithLockAccess(atomicMethodId,objectHashCode,LockOperation.LOCK_ENTER, lockTypeClass().reportLockType());


}
