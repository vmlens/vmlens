package com.anarsoft.race.detection.event.interleave

trait StampedLockExit extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
