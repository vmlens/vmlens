package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.{BarrierContainer, EventContainer}
import com.anarsoft.race.detection.report.element.runelementtype.{BarrierOperationType, ReportOperation}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify
import com.anarsoft.race.detection.report.element.runelementtype.operation.OperationBarrier


trait BarrierWaitEnterEvent extends BarrierEvent {

  def update(container: EventContainer[BarrierEvent]): EventContainer[BarrierEvent] =
    container;

  def onNotify(barrierNotify: Option[BarrierNotifyEvent], f: BarrierEvent => Unit): Unit = {
  }

  def create(): EventContainer[BarrierEvent] = new BarrierContainer(None);

  override def runElementType: ReportOperation =
    new OperationBarrier(BarrierOperationType.WAIT_ENTER, getBarrierKey);

}
