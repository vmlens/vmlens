package com.anarsoft.race.detection.event.syncAction

trait ThreadStartEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.threadStartEvents.add(this);
  }
}
