package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.MonitorContainer
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess

trait MonitorEnterEvent extends MonitorEvent {
  
  override def create(): MonitorContainer = {
    new MonitorContainer(Some(this), None);
  }

  override def update(prevoius: MonitorContainer): MonitorContainer = {
    new MonitorContainer(Some(this), prevoius.monitorExit);
  }

  override def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit = {
    monitorContainer.monitorExit.foreach(f);
  }

  override def runElementType: ReportOperation = {
    new OperationLockAccess(LockOperation.LOCK_ENTER, ReportLockType.MONITOR ,  objectHashCode);
  }
  
}
