package com.anarsoft.race.detection.sortutil

case class MemoryAccessEventGuineaPig(isRead: Boolean, isWrite: Boolean) extends MemoryAccessEvent[MemoryAccessEventGuineaPig] {

}

object MemoryAccessEventGuineaPig {

  def read() = new MemoryAccessEventGuineaPig(true, false);

  def write() = new MemoryAccessEventGuineaPig(false, true);

  def atomic() = new MemoryAccessEventGuineaPig(true, true);
}
