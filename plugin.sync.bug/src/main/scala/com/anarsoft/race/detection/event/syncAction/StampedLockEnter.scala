package com.anarsoft.race.detection.event.syncAction

trait StampedLockEnter extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
