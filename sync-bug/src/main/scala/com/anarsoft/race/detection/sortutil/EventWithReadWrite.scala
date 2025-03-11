package com.anarsoft.race.detection.sortutil

import com.vmlens.trace.agent.bootstrap.MemoryAccessType

trait EventWithReadWrite[EVENT] {

  def operation: Int;

  final def isRead: Boolean = MemoryAccessType.containsRead(operation)

  final def isWrite: Boolean = MemoryAccessType.containsWrite(operation)

}
