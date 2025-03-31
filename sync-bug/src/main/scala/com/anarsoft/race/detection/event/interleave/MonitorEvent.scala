package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode

trait MonitorEvent extends EventForReportElement
  with WithSetStacktraceNode
  with SyncActionEventWithCompareType[MonitorEvent] {

  def create(): MonitorContainer;

  def update(monitorContainer: MonitorContainer): MonitorContainer

  def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit

  def objectHashCode: Long;

  override def compareType(other: MonitorEvent): Int = {
    objectHashCode.compareTo(other.objectHashCode)
  }
  
}
