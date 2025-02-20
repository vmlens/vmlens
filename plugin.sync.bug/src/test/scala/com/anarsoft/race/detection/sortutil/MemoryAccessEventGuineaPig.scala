package com.anarsoft.race.detection.sortutil

import com.vmlens.trace.agent.bootstrap.MemoryAccessType

case class MemoryAccessEventGuineaPig(operation: Int) extends EventWithReadWrite[MemoryAccessEventGuineaPig] {

}

object MemoryAccessEventGuineaPig {

  def read() = new MemoryAccessEventGuineaPig(MemoryAccessType.IS_READ);

  def write() = new MemoryAccessEventGuineaPig(MemoryAccessType.IS_WRITE);

  def readWrite() = new MemoryAccessEventGuineaPig(MemoryAccessType.IS_READ_WRITE);

}
