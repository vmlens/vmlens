package com.anarsoft.race.detection.process.result

import org.roaringbitmap.IntConsumer

class SetHasMonitor(val context : ContextCreateStackTraceGraph) extends IntConsumer {
  
  def accept( ordinal : Int )
  {
   
    context.stackTraceGraphBuilder.setHasMonitor(ordinal);
  }
  
  
}