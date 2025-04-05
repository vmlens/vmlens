package com.anarsoft.race.detection.event.interleave

trait AtomicReadWriteLockEnterEvent  extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }

}
