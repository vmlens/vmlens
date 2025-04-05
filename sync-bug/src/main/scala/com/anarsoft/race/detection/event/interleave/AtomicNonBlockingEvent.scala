package com.anarsoft.race.detection.event.interleave

trait AtomicNonBlockingEvent  extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }

}
