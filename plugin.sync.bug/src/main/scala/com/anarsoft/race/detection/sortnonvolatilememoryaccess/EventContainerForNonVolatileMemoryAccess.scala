package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.anarsoft.race.detection.sortutil.EventContainer

class EventContainerForNonVolatileMemoryAccess[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(private var read: Option[EVENT],
 private var write: Option[EVENT]) extends EventContainer[EVENT] {

  override def getOrCreateElement(event: EVENT): EventContainer[EVENT] = {
    if (event.isRead) {
      new EventContainerForNonVolatileMemoryAccess(Some(event), write);
    } else {
      new EventContainerForNonVolatileMemoryAccess(read, Some(event));
    }
  }

  override def foreachOpposite(event: EVENT, f: EVENT => Unit): Unit = {
    if (event.isRead) {
      write.foreach(f);
    } else {
      read.foreach(f);
    }
  }
}

object EventContainerForNonVolatileMemoryAccess {

  def apply[EVENT <: NonVolatileMemoryAccessEvent[EVENT]](event: EVENT): EventContainerForNonVolatileMemoryAccess[EVENT] = {
    if (event.isRead) {
      new EventContainerForNonVolatileMemoryAccess(Some(event), None);
    } else {
      new EventContainerForNonVolatileMemoryAccess(None, Some(event));
    }
  }
}
