package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.impl.LockTypeClassFromId
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess


trait LockEnterEvent extends LockEvent with WithLockEnterEvent  with LockTypeClassFromId[WithLockEnterEvent] {

  override def runElementType : ReportOperation =  
    new OperationLockAccess(LockOperation.LOCK_ENTER, lockTypeClass().reportLockType(), objectHashCode);
  
  
}
