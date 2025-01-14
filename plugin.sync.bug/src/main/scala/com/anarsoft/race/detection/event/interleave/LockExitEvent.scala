package com.anarsoft.race.detection.event.interleave

trait LockExitEvent extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
