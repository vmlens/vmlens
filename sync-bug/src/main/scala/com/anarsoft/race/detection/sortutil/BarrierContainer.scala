package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.event.interleave.{BarrierEvent, MonitorEnterEvent, MonitorEvent, MonitorExitEvent}

class BarrierContainer(val barrierWait: Option[BarrierEvent],
                       val barrierNotify: Option[BarrierEvent])
              extends EventContainer[BarrierEvent] {

  override def put(elem: BarrierEvent): EventContainer[BarrierEvent] =
    elem.update(this);


  override def foreachOpposite(elem: BarrierEvent, f: BarrierEvent => Unit): Unit =
    elem.foreachOpposite(this, f);

}


