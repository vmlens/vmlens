package com.anarsoft.race.detection.event.syncaction

trait VolatileArrayAccessEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
  }
}
