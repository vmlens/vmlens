package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.anarsoft.race.detection.report.element.runelementtype.{MethodWithLockAccess, ReportLockType, ReportOperation, LockOperation}

trait AtomicReadWriteLockEnterEvent extends AtomicReadWriteLockEvent with WithLockEnterEvent with LockTypeClassFromId[WithLockEnterEvent] {

  def  atomicMethodId  : Int;

  override def runElementType: ReportOperation =
    new MethodWithLockAccess(atomicMethodId,objectHashCode,LockOperation.LOCK_ENTER, lockTypeClass().reportLockType());


}
