package com.anarsoft.race.detection.event.syncaction

trait ThreadStartEvent extends LoadedSyncActionEvent {
  override def addToContext(context: LoadedSyncActionContext): Unit = {
    context.threadStartEvents.add(this);
  }
}
