package com.anarsoft.race.detection.process.directMemory

import com.anarsoft.race.detection.process.read._;
import  com.anarsoft.race.detection.process.gen._;


class DirectMemoryReadCallback(val context : ContextReadDirectMemory)   extends ReadCallback[DirectMemoryEvent] with DirectMemoryVisitor  {
  
  
  def visit( in :  VolatileDirectMemoryEventGen)
  {
    
    
     //  context.directVolatileSyncPointMap.add(in , in.objectHashCode  );
    
    // context.fieldPipelineCollection.addVolatileDirectEvent(in);
  }
  
  
  def onEvent(event: com.anarsoft.race.detection.process.directMemory.DirectMemoryEvent)
  {
   event.accept(this);
  }
  
  
  
  def readSlidingWindowId(id: Int)
  {
   // context.fieldPipelineCollection.startReadingDirectMemory();
  }
  
}