package com.anarsoft.race.detection.sortMemoryAccess

import com.anarsoft.race.detection.util.ThreadIdAndMethodCounter

trait MemoryAccessEvent extends ThreadIdAndMethodCounter {

  def operation: Int;

  def isNewMemoryLocation(other: MemoryAccessEvent): Boolean;

}
