package com.anarsoft.race.detection.event.interleave

trait AtomicReadWriteLockEvent extends LoadedInterleaveActionEvent  with WithLockEvent {

  override def addToContext(context: LoadedInterleaveActionContext): Unit = {
    context.atomicReadWriteLockEvents.add(this);
  }
  
}  
