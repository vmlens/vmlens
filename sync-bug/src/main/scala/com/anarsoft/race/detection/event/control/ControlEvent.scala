package com.anarsoft.race.detection.event.control

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId

trait ControlEvent extends EventWithLoopAndRunId {
  
  def process(context : ProcessContext): Unit;
  
  

}
