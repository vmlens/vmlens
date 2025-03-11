package com.anarsoft.race.detection.event.interleave

import com.vmlens.report.operationtextfactory.{MonitorOperation, MonitorTextFactory, OperationTextFactory}


trait MonitorEnterEvent extends LoadedInterleaveActionEvent with MonitorEvent {


  override def create(): MonitorContainer = {
    new MonitorContainer(Some(this), None);
  }

  override def update(prevoius: MonitorContainer): MonitorContainer = {
    new MonitorContainer(Some(this), prevoius.monitorExit);
  }

  override def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit = {
    monitorContainer.monitorExit.foreach(f);
  }

  override def operationTextFactory: OperationTextFactory = {
    new MonitorTextFactory(MonitorOperation.MONITOR_ENTER, objectHashCode);
  }


  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.monitorEvents.add(this);
  }

}
