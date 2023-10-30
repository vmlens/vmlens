package com.anarsoft.race.detection.event.syncAction

trait LoadedSyncActionEvent {

  def addToContext(context: LoadedSyncActionContext): Unit;

}
