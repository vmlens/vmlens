package com.anarsoft.race.detection.event.interleave

trait MonitorExitEvent extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
