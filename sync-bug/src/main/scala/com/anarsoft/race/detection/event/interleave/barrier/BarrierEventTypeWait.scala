package com.anarsoft.race.detection.event.interleave.barrier

import com.anarsoft.race.detection.event.interleave.BarrierEvent
import com.anarsoft.race.detection.sortutil.BarrierContainer
import com.vmlens.report.runelementtype.BarrierOperationType

class BarrierEventTypeWait extends BarrierEventType {
  
  override def create(event : BarrierEvent): BarrierContainer = new BarrierContainer(Option(event),None);

  override def update(event: BarrierEvent, barrierContainer: BarrierContainer): BarrierContainer =
    new BarrierContainer(Option(event), barrierContainer.barrierNotify);
    

  override def foreachOpposite(event: BarrierEvent, barrierContainer: BarrierContainer, f: BarrierEvent => Unit): Unit =
    barrierContainer.barrierNotify.foreach(f);

  override def getOperationType: BarrierOperationType = BarrierOperationType.WAIT
}
