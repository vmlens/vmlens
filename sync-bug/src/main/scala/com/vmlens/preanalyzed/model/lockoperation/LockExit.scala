package com.vmlens.preanalyzed.model.lockoperation

import com.vmlens.preanalyzed.model.{LockType, ReadLock, WriteLock, ReentrantLock}
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl
class LockExit extends LockOperation {

  override def create(lockType: LockType): AbstractMethodType = {
     lockType match {
       case ReadLock() => {
        methodtypeimpl.LockMethod.EXIT_READ_LOCK;
      }
      case WriteLock() => {
        methodtypeimpl.LockMethod.EXIT_WRITE_LOCK;
      }
      case ReentrantLock() => {
        methodtypeimpl.LockMethod.EXIT_REENTRANT_LOCK;
      }
    }
  }
}
