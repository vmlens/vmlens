package com.anarsoft.race.detection.sortNonVolatileMemoryAccess

class MemoryAccessEventBuilder {

  var positionInRun = 0;
  var threadId = 1L;
  var typeId = 1;

  def incrementPositionInRun(delta: Int): Unit = {
    positionInRun = positionInRun + delta;
  }

  def threadId(threadId: Long): MemoryAccessEventBuilder = {
    this.threadId = threadId;
    this;
  }

  def typeId(typeId: Int): MemoryAccessEventBuilder = {
    this.typeId = typeId;
    this;
  }

  def read(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, true, positionInRun, threadId);
    positionInRun = positionInRun + 1;
    result;
  }

  def write(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, false, positionInRun, threadId);
    positionInRun = positionInRun + 1;
    result;
  }

}
