package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.sortutil.{BarrierContainer, EventContainer}
import com.vmlens.report.input.run.{BarrierOperation, BarrierOperationType, RunElementType}


trait BarrierWaitExitEvent extends BarrierEvent {

  def update(container: EventContainer[BarrierEvent]): EventContainer[BarrierEvent] =
    container;

  def onNotify(barrierNotify: Option[BarrierNotifyEvent], f: BarrierEvent => Unit): Unit = {
    barrierNotify.foreach(f);
  }

  def create(): EventContainer[BarrierEvent] = new BarrierContainer(None);
  
  override def runElementType: RunElementType =
    new BarrierOperation(BarrierOperationType.WAIT_EXIT, getBarrierKey);

}
