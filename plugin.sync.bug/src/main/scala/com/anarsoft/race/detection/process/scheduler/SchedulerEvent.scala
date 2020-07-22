package com.anarsoft.race.detection.process.scheduler

import com.anarsoft.race.detection.process.gen._;


trait SchedulerEvent {
  
    def accept(visitor : SchedulerVisitor);
  
  
}