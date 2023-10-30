package com.anarsoft.race.detection.event.syncAction

trait VolatileArrayAccessEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
