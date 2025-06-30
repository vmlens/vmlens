package com.anarsoft.race.detection.event.impl

import com.anarsoft.race.detection.event.interleave.WithLockEventGeneric
import com.anarsoft.race.detection.event.interleave.locktype.{LockType, LockTypeBuilder}
import com.vmlens.trace.agent.bootstrap.lock.LockTypes

trait LockTypeClassFromId[EVENT <: WithLockEventGeneric[EVENT]] {

  def lockTypeClass(): LockType[EVENT] =  {
    val builder = new LockTypeBuilder[EVENT]();
    new LockTypes().accept(lockType, builder)
    builder.build();
  }

  def lockType: Int;
  
}
