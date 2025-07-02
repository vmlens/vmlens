package com.anarsoft.race.detection.event.interleave.barrier

import com.anarsoft.race.detection.event.interleave.BarrierEvent
import com.anarsoft.race.detection.sortutil.BarrierContainer
import com.vmlens.report.runelementtype.BarrierOperationType

class BarrierEventTypeNotify extends BarrierEventType {

  override def create(event : BarrierEvent): BarrierContainer = new BarrierContainer(None,Option(event));

  override def update(event: BarrierEvent, barrierContainer: BarrierContainer): BarrierContainer =
    new BarrierContainer(barrierContainer.barrierWait,Option(event));

  override def foreachOpposite(event: BarrierEvent, barrierContainer: BarrierContainer, f: BarrierEvent => Unit): Unit = {
    // Nothing to do
  }

  override def getOperationType: BarrierOperationType = BarrierOperationType.NOTIFY
}
