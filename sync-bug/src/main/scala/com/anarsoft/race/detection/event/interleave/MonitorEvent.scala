package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.createpartialordersyncaction.SyncActionEventWithCompareType
import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode
import com.vmlens.report.assertion.{EventWithAssertion, OnDescriptionAndEvent}

trait MonitorEvent extends EventForReportElement
  with WithSetStacktraceNode
  with SyncActionEventWithCompareType[MonitorEvent]
  with EventWithAssertion {

  def create(): MonitorContainer;

  def update(monitorContainer: MonitorContainer): MonitorContainer

  def foreachOpposite(monitorContainer: MonitorContainer, f: MonitorEvent => Unit): Unit

  def objectHashCode: Long;

  override def compareType(other: MonitorEvent): Int = {
    objectHashCode.compareTo(other.objectHashCode)
  }

  override def add(onDescriptionAndEvent: OnDescriptionAndEvent): Unit = {

  }
}
