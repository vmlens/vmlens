package com.anarsoft.race.detection.event.interleave

trait StampedLockEnter extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
