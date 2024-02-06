package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

import com.anarsoft.race.detection.sortUtil.EventContainer

class NonVolatileMemoryAccessEventContainer[EVENT <: NonVolatileMemoryAccessEvent[EVENT]]
(private var read: Option[EVENT],
 private var write: Option[EVENT]) extends EventContainer[EVENT] {

  override def create(event: EVENT): EventContainer[EVENT] = {
    if (event.isRead) {
      new NonVolatileMemoryAccessEventContainer(Some(event), write);
    } else {
      new NonVolatileMemoryAccessEventContainer(read, Some(event));
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

object NonVolatileMemoryAccessEventContainer {

  def apply[EVENT <: NonVolatileMemoryAccessEvent[EVENT]](event: EVENT): NonVolatileMemoryAccessEventContainer[EVENT] = {
    if (event.isRead) {
      new NonVolatileMemoryAccessEventContainer(Some(event), None);
    } else {
      new NonVolatileMemoryAccessEventContainer(None, Some(event));
    }
  }
}
