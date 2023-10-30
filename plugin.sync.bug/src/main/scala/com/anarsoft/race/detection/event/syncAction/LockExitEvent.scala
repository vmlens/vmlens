package com.anarsoft.race.detection.event.syncAction

trait LockExitEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
