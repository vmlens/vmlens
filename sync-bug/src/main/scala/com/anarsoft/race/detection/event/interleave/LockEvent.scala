package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.reportbuilder.EventForReportElement
import com.anarsoft.race.detection.setstacktrace.WithSetStacktraceNode

trait LockEvent extends LoadedInterleaveActionEvent  with WithLockEvent {

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.lockEvents.add(this);
  }
  
}
