package com.anarsoft.race.detection.event.interleave

trait ThreadJoinedEvent extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
