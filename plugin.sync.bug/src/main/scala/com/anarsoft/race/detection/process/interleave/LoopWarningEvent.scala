package com.anarsoft.race.detection.process.interleave

import com.anarsoft.race.detection.process.scheduler._

trait LoopWarningEvent extends SchedulerEvent {
    
   def loopId : Int;
  
}