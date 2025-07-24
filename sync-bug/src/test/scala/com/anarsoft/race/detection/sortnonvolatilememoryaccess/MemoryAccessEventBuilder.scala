package com.anarsoft.race.detection.sortnonvolatilememoryaccess

import com.vmlens.trace.agent.bootstrap.MemoryAccessType

class MemoryAccessEventBuilder(val typeId : Int) {
  

  def threadId(threadIndex: Int): MemoryAccessEventBuilderForThreadIndex = {
    new MemoryAccessEventBuilderForThreadIndex(typeId,threadIndex);
  }
  
}
