package com.anarsoft.race.detection.event.syncAction

import com.anarsoft.race.detection.sortutil.MemoryAccessEvent
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType

trait VolatileMemoryAccessEvent[EVENT] extends MemoryAccessEvent[EVENT] {

  def operation: Int;

  def isRead: Boolean = (operation | MemoryAccessType.IS_READ) == MemoryAccessType.IS_READ;

  def isWrite: Boolean = (operation | MemoryAccessType.IS_WRITE) == MemoryAccessType.IS_WRITE;

}
