package com.anarsoft.race.detection.event.syncaction

trait LockExitEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
