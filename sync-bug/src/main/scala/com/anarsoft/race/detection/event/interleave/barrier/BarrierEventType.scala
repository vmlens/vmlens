package com.anarsoft.race.detection.event.interleave.barrier

import com.anarsoft.race.detection.event.interleave.BarrierEvent
import com.anarsoft.race.detection.sortutil.BarrierContainer
import com.vmlens.report.runelementtype.BarrierOperationType

trait BarrierEventType {

  def getOperationType: BarrierOperationType;
  
  def create(event : BarrierEvent): BarrierContainer;

  def update(event : BarrierEvent, barrierContainer: BarrierContainer): BarrierContainer;

  def foreachOpposite(event : BarrierEvent, barrierContainer: BarrierContainer, f: BarrierEvent => Unit): Unit;
  
}
