package com.vmlens.preanalyzed.model

case class MethodWithLock(name : String, desc : String, lockType : ReadOrWriteLockType) extends AtomicMethod
