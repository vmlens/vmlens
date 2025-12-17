package com.anarsoft.race.detection.event.interleave


import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationMethodWithLockAccess
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}


trait AtomicReadWriteLockExitEvent  extends AtomicReadWriteLockEvent with WithLockExitEvent with LockTypeClassFromId[WithLockExitEvent] {

  def atomicMethodId: Int;

  override def runElementType: ReportOperation =
    new OperationMethodWithLockAccess(atomicMethodId,objectHashCode, LockOperation.LOCK_EXIT, lockTypeClass().reportLockType());


}
