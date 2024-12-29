package com.anarsoft.race.detection.event.syncaction

trait LockEnterEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
