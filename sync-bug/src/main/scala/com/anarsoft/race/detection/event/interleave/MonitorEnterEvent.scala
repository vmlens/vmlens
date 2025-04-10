package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.MonitorContainer
import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType}


trait MonitorEnterEvent extends   MonitorEvent {
  
  override def create(): MonitorContainer = {
    new MonitorContainer(Some(this), None);
  }

  override def update(prevoius: MonitorContainer): MonitorContainer = {
    new MonitorContainer(Some(this), prevoius.monitorExit);
  }

  override def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit = {
    monitorContainer.monitorExit.foreach(f);
  }

  override def runElementType: RunElementType = {
    new LockAccess(LockOperation.LOCK_ENTER, ReportLockType.MONITOR ,  objectHashCode);
  }
  
}
