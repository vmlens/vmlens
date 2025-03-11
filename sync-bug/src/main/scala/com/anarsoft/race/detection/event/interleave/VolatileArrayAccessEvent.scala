package com.anarsoft.race.detection.event.interleave

trait VolatileArrayAccessEvent extends LoadedInterleaveActionEvent {
  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
  }
}
