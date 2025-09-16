package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.{BarrierContainer, EventContainer}
import com.vmlens.report.runelementtype.{BarrierOperation, BarrierOperationType, RunElementType}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify


trait BarrierWaitEnterEvent extends BarrierEvent {

  def update(container: EventContainer[BarrierEvent]): EventContainer[BarrierEvent] =
    container;

  def onNotify(barrierNotify: Option[BarrierNotifyEvent], f: BarrierEvent => Unit): Unit = {
  }

  def create(): EventContainer[BarrierEvent] = new BarrierContainer(None);

  override def runElementType: RunElementType =
    new BarrierOperation(BarrierOperationType.WAIT_ENTER, getBarrierKey);

}
