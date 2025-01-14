package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.distribute.EventWithLoopAndRunId

trait LoadedInterleaveActionEvent extends EventWithLoopAndRunId {

  def addToContext(context: LoadedInterleaveActionContext): Unit;

}
