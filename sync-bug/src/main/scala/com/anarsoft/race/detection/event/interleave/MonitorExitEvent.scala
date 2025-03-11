package com.anarsoft.race.detection.event.interleave

import com.vmlens.report.operationtextfactory.{MonitorOperation, MonitorTextFactory, OperationTextFactory}

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

  override def operationTextFactory: OperationTextFactory = {
    new MonitorTextFactory(MonitorOperation.MONITOR_EXIT, objectHashCode);
  }

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.monitorEvents.add(this);
  }

}
