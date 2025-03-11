package com.anarsoft.race.detection.event.interleave

trait LockEnterEvent extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
