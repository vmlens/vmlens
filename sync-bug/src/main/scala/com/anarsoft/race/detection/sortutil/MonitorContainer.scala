package com.anarsoft.race.detection.sortutil

import com.anarsoft.race.detection.event.interleave.{MonitorEnterEvent, MonitorEvent, MonitorExitEvent}
import com.anarsoft.race.detection.sortutil.EventContainer

class MonitorContainer(val monitorEnter: Option[MonitorEnterEvent], 
                       val monitorExit: Option[MonitorExitEvent])
  extends EventContainer[MonitorEvent] {

  override def put(elem: MonitorEvent): EventContainer[MonitorEvent] = 
    elem.update(this);
  

  override def foreachOpposite(elem: MonitorEvent, f: MonitorEvent => Unit): Unit = 
    elem.foreachOpposite(this, f);
    
}

object MonitorContainer {

  def apply(event: MonitorEvent): MonitorContainer = {
    event.create();
  }

}
