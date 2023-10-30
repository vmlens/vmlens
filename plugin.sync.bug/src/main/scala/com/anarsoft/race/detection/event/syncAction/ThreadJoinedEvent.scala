package com.anarsoft.race.detection.event.syncAction

trait ThreadJoinedEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
