package com.anarsoft.race.detection.event.impl

import com.anarsoft.race.detection.event.interleave.locktype.{LockType, ReentrantLock, WriteLock}
import com.anarsoft.race.detection.event.interleave.{WithLockEventGeneric, WithLockExitEvent}
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey

trait LockTypeClassFromCategory[EVENT <: WithLockEventGeneric[EVENT]]  {

  def lockKeyCategory  : Int;

  def lockTypeClass() : LockType[EVENT] = {
    lockKeyCategory match {
      case LockKey.CATEGORY_MONITOR =>{
        throw new RuntimeException("should not happen") 
      }
      case LockKey.CATEGORY_REENTRANT_LOCK => {
        ReentrantLock[EVENT]()
      }

      case LockKey.CATEGORY_READ_WRITE_LOCK => {
        WriteLock[EVENT]()
      }  
    }

  }


}
