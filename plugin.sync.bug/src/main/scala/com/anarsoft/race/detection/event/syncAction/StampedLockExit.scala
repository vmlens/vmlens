package com.anarsoft.race.detection.event.syncAction

trait StampedLockExit extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
