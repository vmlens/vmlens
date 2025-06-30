package com.anarsoft.race.detection.event.interleave

import com.anarsoft.race.detection.event.interleave.locktype.{LockType, LockTypeBuilder}
import com.vmlens.trace.agent.bootstrap.lock.LockTypes

trait WithLockEventGeneric[EVENT <: WithLockEventGeneric[EVENT]] extends WithLockEvent {

  

  def lockTypeClass(): LockType[EVENT];
  
}
