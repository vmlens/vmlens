package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.{BarrierContainer, EventContainer}
import com.anarsoft.race.detection.report.element.runelementtype.{BarrierOperationType, ReportOperation}
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationBarrier


trait BarrierWaitExitEvent extends BarrierEvent {

  def update(container: EventContainer[BarrierEvent]): EventContainer[BarrierEvent] =
    container;

  def onNotify(barrierNotify: Option[BarrierNotifyEvent], f: BarrierEvent => Unit): Unit = {
    barrierNotify.foreach(f);
  }

  def create(): EventContainer[BarrierEvent] = new BarrierContainer(None);
  
  override def runElementType: ReportOperation =
    new OperationBarrier(BarrierOperationType.WAIT_EXIT, getBarrierKey);

}
