package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createdominatortreeevent.CreateDominatorTreeContext
import com.anarsoft.race.detection.sortutil.MonitorContainer
import com.anarsoft.race.detection.report.element.runelementtype.{LockOperation, ReportLockType, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationLockAccess

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

  override def runElementType: ReportOperation = {
    new OperationLockAccess(LockOperation.LOCK_EXIT,  ReportLockType.MONITOR,objectHashCode);
  }

  override def add(context: CreateDominatorTreeContext): Unit = {
    context.stack.monitorExit(context.graph,context.alreadyAdded)
  }

}
