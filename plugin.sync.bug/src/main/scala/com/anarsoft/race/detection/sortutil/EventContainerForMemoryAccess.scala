package com.anarsoft.race.detection.sortutil


class EventContainerForMemoryAccess[EVENT <: MemoryAccessEvent[EVENT]]
(private val read: Option[EVENT], private val write: Option[EVENT]) extends EventContainer[EVENT] {

  override def put(event: EVENT): EventContainer[EVENT] = EventContainerForMemoryAccess(read, write, event);


  override def foreachOpposite(event: EVENT, f: EVENT => Unit): Unit = {
    if (event.isRead) {
      write.foreach(f);
    }
    if (event.isWrite) {
      read.foreach(f)
    }
  }
}

object EventContainerForMemoryAccess {

  def apply[EVENT <: MemoryAccessEvent[EVENT]](event: EVENT): EventContainer[EVENT] =
    apply(None, None, event);

  def apply[EVENT <: MemoryAccessEvent[EVENT]](existingRead: Option[EVENT],
                                               existingWrite: Option[EVENT], event: EVENT) = {
    var resultRead = existingRead;
    var resultWrite = existingWrite;

    if (event.isRead) {
      resultRead = Some(event)
    }
    if (event.isWrite) {
      resultWrite = Some(event)
    }
    new EventContainerForMemoryAccess(resultRead, resultWrite)
  }
}
