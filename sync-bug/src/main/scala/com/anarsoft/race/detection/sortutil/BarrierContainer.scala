package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.event.interleave.{BarrierEvent, BarrierNotifyEvent, MonitorEvent, MonitorExitEvent}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify

class BarrierContainer(val barrierNotify: Option[BarrierNotifyEvent])
              extends EventContainer[BarrierEvent] {

  override def put(elem: BarrierEvent): EventContainer[BarrierEvent] =
    elem.update(this);


  override def foreachOpposite(elem: BarrierEvent, f: BarrierEvent => Unit): Unit =
    elem.onNotify( barrierNotify,f);

}


