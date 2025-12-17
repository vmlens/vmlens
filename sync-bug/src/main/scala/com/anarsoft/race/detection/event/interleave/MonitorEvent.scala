package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.report.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.anarsoft.race.detection.sortutil.MonitorContainer

trait MonitorEvent extends LoadedInterleaveActionEvent 
  with EventForReportElement
  with WithSetStacktraceNode
  with SyncActionEventWithCompareType[MonitorEvent] {

  def create(): MonitorContainer;

  def update(monitorContainer: MonitorContainer): MonitorContainer

  def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit

  def objectHashCode: Long;

  override def compareType(other: MonitorEvent): Int = {
    objectHashCode.compareTo(other.objectHashCode)
  }

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.monitorEvents.add(this);
  }
  
}
