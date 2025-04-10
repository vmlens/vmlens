package com.anarsoft.race.detection.event.interleave.locktype

import com.anarsoft.race.detection.event.interleave.WithLockEventGeneric
import com.vmlens.trace.agent.bootstrap.lock.LockTypeVisitor

class LockTypeBuilder[EVENT <:  WithLockEventGeneric[EVENT]]  extends LockTypeVisitor {

  var result : LockType[EVENT] = null;
  
  override def visitRead(): Unit = {
    result =  ReadLock[EVENT]();
  }

  override def visitWrite(): Unit = {
    result = WriteLock[EVENT]();
  }

  override def visitReentrant(): Unit = {
    result = ReentrantLock[EVENT]();
  }
  
  def build() : LockType[EVENT] = result;
  
}
