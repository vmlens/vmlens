package com.anarsoft.race.detection.sortnonvolatilememoryaccess

class MemoryAccessEventBuilder {

  var positionInRun = 0;
  var threadIndex = 1;
  var typeId = 1;

  def incrementPositionInRun(delta: Int): Unit = {
    positionInRun = positionInRun + delta;
  }

  def threadId(threadIndex: Int): MemoryAccessEventBuilder = {
    this.threadIndex = threadIndex;
    this;
  }

  def typeId(typeId: Int): MemoryAccessEventBuilder = {
    this.typeId = typeId;
    this;
  }

  def read(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, true, positionInRun, threadIndex);
    positionInRun = positionInRun + 1;
    result;
  }

  def write(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, false, positionInRun, threadIndex);
    positionInRun = positionInRun + 1;
    result;
  }

}
