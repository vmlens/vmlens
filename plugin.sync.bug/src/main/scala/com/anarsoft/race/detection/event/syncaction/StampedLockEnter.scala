package com.anarsoft.race.detection.event.syncaction

trait StampedLockEnter extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
