package com.anarsoft.race.detection.event.syncaction

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId

trait LoadedSyncActionEvent extends EventWithLoopAndRunId {

  def addToContext(context: LoadedSyncActionContext): Unit;

}
