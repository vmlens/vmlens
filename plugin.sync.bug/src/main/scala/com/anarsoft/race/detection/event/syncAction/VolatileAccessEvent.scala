package com.anarsoft.race.detection.event.syncAction

trait VolatileAccessEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.addVolatileAccessEvent(this);
  }
}
