package com.anarsoft.race.detection.event.syncaction

trait StampedLockExit extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
