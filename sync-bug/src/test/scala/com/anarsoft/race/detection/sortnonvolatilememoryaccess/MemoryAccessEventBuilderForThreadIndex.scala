package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.vmlens.trace.agent.bootstrap.MemoryAccessType

class MemoryAccessEventBuilderForThreadIndex(val typeId : Int, val threadIndex : Int) {

  var positionInRun = 0;

  def incrementPositionInRun(delta: Int): Unit = {
    positionInRun = positionInRun + delta;
  }

  def read(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, MemoryAccessType.IS_READ, positionInRun, threadIndex);
    positionInRun = positionInRun + 1;
    result;
  }

  def write(): NonVolatileMemoryAccessEventGuineaPig = {
    val result = new NonVolatileMemoryAccessEventGuineaPig(typeId, MemoryAccessType.IS_WRITE, positionInRun, threadIndex);
    positionInRun = positionInRun + 1;
    result;
  }
  

}
