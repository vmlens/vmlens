package com.anarsoft.race.detection.event.interleave

trait AtomicReadWriteLockExitEvent  extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }

}
