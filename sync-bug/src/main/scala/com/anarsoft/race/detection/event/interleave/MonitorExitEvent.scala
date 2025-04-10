package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.MonitorContainer
import com.vmlens.report.runelementtype.{LockAccess, LockOperation, ReportLockType, RunElementType}

trait MonitorExitEvent extends LoadedInterleaveActionEvent with MonitorEvent {

  override def create(): MonitorContainer = {
    new MonitorContainer(None, Some(this));
  }

  override def update(prevoius: MonitorContainer): MonitorContainer = {
    new MonitorContainer(prevoius.monitorEnter, Some(this));
  }

  override def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit = {
    monitorContainer.monitorEnter.foreach(f);
  }

  override def runElementType: RunElementType = {
    new LockAccess(LockOperation.LOCK_EXIT,  ReportLockType.MONITOR,objectHashCode);
  }

 

}
