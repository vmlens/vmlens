package com.anarsoft.race.detection.createPartialOrder

import com.anarsoft.race.detection.sortUtil.EventContainer

class EventContainerMonitorGuineaPig(private val monitorExitEvent: Option[SyncActionEventMonitorGuineaPig]) extends EventContainer[SyncActionEventMonitorGuineaPig] {
  override def getOrCreateElement(event: SyncActionEventMonitorGuineaPig):
  EventContainer[SyncActionEventMonitorGuineaPig] = {
    if (event.isEnter) {
      this
    } else {
      new EventContainerMonitorGuineaPig(Some(event));
    }
  }

  override def foreachOpposite(event: SyncActionEventMonitorGuineaPig,
                               f: SyncActionEventMonitorGuineaPig => Unit): Unit = {
    if (event.isEnter) {
      monitorExitEvent.foreach(f)
    }
  }
}

object EventContainerMonitorGuineaPig {

  def apply(event: SyncActionEventMonitorGuineaPig): EventContainerMonitorGuineaPig = {
    if (event.isEnter) {
      new EventContainerMonitorGuineaPig(None);
    } else {
      new EventContainerMonitorGuineaPig(Some(event));
    }
  }

}
