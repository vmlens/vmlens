package com.anarsoft.race.detection.event.syncaction

trait ThreadJoinedEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
