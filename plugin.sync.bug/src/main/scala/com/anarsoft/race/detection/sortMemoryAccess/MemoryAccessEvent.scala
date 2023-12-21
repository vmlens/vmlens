package com.anarsoft.race.detection.sortMemoryAccess

import com.anarsoft.race.detection.stacktrace.ThreadIdAndMethodCounter

trait MemoryAccessEvent extends ThreadIdAndMethodCounter {

  def operation: Int;

  def isNewMemoryLocation(other: MemoryAccessEvent): Boolean;

}
