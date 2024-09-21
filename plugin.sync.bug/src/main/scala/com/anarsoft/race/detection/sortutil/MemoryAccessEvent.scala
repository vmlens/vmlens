package com.anarsoft.race.detection.sortutil

trait MemoryAccessEvent[EVENT] {
  def isRead: Boolean

  def isWrite: Boolean;
}
